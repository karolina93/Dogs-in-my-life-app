package pl.dogsinmylife.breed.breedList;

import java.util.List;

import pl.dogsinmylife.BasePresenter;
import pl.dogsinmylife.BaseView;
import pl.dogsinmylife.models.Breed;


public interface BreedListContract {

    interface View extends BaseView<Presenter> {

        void setNoBreeds(boolean noBreeds);

        void loadBreeds(List<Breed> breeds);

        void updateBreeds(List<Breed> breeds);

    }

    interface Presenter extends BasePresenter {
        void refresh();

        void updateBreed(Breed breed, boolean liked);
    }
}
