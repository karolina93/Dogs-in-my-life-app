package pl.dogsinmylife.breed.breedList;

import android.support.annotation.NonNull;
import android.util.Log;

import pl.dogsinmylife.breed.repository.ItemChangedInterface;
import pl.dogsinmylife.models.Breed;
import pl.dogsinmylife.breed.repository.BreedRepository;


public class BreedListPresenter implements BreedListContract.Presenter, ItemChangedInterface {

    private BreedListContract.View view;
    private BreedRepository breedRepository;
    private boolean showLiked;
    private boolean viewStarted = false;

    public BreedListPresenter(@NonNull BreedListContract.View view, @NonNull BreedRepository breedRepository, boolean showLiked) {
        this.view = view;
        this.breedRepository = breedRepository;
        this.showLiked = showLiked;
        view.setPresenter(this);
        setDataListener();
    }

    private void setDataListener() {
        breedRepository.setOnItemChangedListener(this);
    }

    @Override
    public void start() {
        view.loadBreeds(breedRepository.getBreedsFromDatabase(showLiked));
        viewStarted = true;
    }

    @Override
    public void stop() {
    }

    @Override
    public void refresh() {
        view.updateBreeds(breedRepository.getBreedsFromDatabase(showLiked));
    }

    @Override
    public void updateBreed(Breed breed, boolean liked) {
        Log.v("Breed presenter", "Update breed + " + breed.getName());
        breedRepository.updateBreed(breed, liked);
    }

    // callback from repository
    @Override
    public void onItemChanged(Breed breed) {
        Log.v("Breed presenter", "Item changed" + breed.getName());
        if (view != null && viewStarted) {
            view.updateBreeds(breedRepository.getBreedsFromDatabase(showLiked));
        }
    }
}
