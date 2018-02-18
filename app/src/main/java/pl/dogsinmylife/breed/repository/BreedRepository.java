package pl.dogsinmylife.breed.repository;

import android.content.SharedPreferences;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.dogsinmylife.models.Breed;
import pl.dogsinmylife.models.Breed_Table;
import pl.dogsinmylife.models.BreedsResponse;
import pl.dogsinmylife.rest.NoConnectivityException;
import pl.dogsinmylife.rest.RestApi;
import pl.dogsinmylife.database.DB;
import pl.dogsinmylife.utils.SharedUtils;


public class BreedRepository implements BreedRepositoryInterface {

    RestApi api;
    SharedPreferences sharedPreferences;
    List<ItemChangedInterface> listeners;

    public BreedRepository(RestApi api, SharedPreferences sharedPreferences) {
        this.api = api;
        this.sharedPreferences = sharedPreferences;
        listeners = new ArrayList<>();
    }

    public void setOnItemChangedListener(ItemChangedInterface onItemChangedListener) {
        listeners.add(onItemChangedListener);
    }

    @Override
    public void updateBreed(Breed breed, boolean liked) {
        breed.setLiked(liked);
        breed.update();
        for (ItemChangedInterface listener : listeners) {
            if (listener != null) {
                listener.onItemChanged(breed);
            }
        }
    }

    @Override
    public void fetchBreedsFromApi(final FetchBreedsCallback callback) {
        api.getAllBreeds()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BreedsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BreedsResponse breedsResponse) {
                        if (breedsResponse.getStatus().equals("success")) {
                            callback.onSuccess(breedsResponse.getBreedList());
                        } else {
                            callback.onError(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e instanceof NoConnectivityException);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public List<Breed> getBreedsFromDatabase(boolean liked) {
        return SQLite.select()
                .from(Breed.class)
                .where(Breed_Table.liked.eq(liked))
                .orderBy(Breed_Table.name, true)
                .queryList();
    }

    @Override
    public void saveBreedsToDatabase(final List<Breed> breeds) {
        FlowManager.getDatabase(DB.class).executeTransaction(databaseWrapper -> {
            for (Breed breed : breeds) {
                breed.save();
            }
        });
    }

    @Override
    public boolean isFirstAppRun() {
        return SharedUtils.isFirstRun(sharedPreferences);
    }
}
