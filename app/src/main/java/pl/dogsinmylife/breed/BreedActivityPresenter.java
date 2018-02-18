package pl.dogsinmylife.breed;

import android.support.annotation.NonNull;

import java.util.List;

import pl.dogsinmylife.models.Breed;
import pl.dogsinmylife.breed.repository.BreedRepository;
import pl.dogsinmylife.breed.repository.BreedRepositoryInterface;


public class BreedActivityPresenter implements BreedActivityContract.Presenter, BreedRepositoryInterface.FetchBreedsCallback {

    BreedActivityContract.View view;
    BreedRepository breedRepository;

    public BreedActivityPresenter(@NonNull BreedActivityContract.View view, BreedRepository breedRepository) {
        this.view = view;
        this.breedRepository = breedRepository;
    }

    @Override
    public void start() {
        if (breedRepository.isFirstAppRun()) {
            fetchData();
        } else {
            view.refreshTabs();
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void fetchData() {
        view.progressView(true);
        breedRepository.fetchBreedsFromApi(this);
    }

    //fetch data from api callbacks
    @Override
    public void onSuccess(List<Breed> breeds) {
        breedRepository.saveBreedsToDatabase(breeds);
        view.progressView(false);
        view.refreshTabs();
    }

    @Override
    public void onError(boolean noInternetError) {
        if (noInternetError) {
            view.showToastNoNetwork();
        } else {
            view.showToastServerError();
        }
        view.progressView(false);
    }

}
