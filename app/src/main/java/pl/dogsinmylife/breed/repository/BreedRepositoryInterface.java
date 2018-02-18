package pl.dogsinmylife.breed.repository;

import java.util.List;

import pl.dogsinmylife.models.Breed;

public interface BreedRepositoryInterface {

    interface FetchBreedsCallback {

        void onSuccess(List<Breed> breeds);

        void onError(boolean noInternetError);
    }

    void fetchBreedsFromApi(FetchBreedsCallback callback);

    List<Breed> getBreedsFromDatabase(boolean liked);

    void saveBreedsToDatabase(List<Breed> breeds);

    void updateBreed(Breed breed, boolean liked);

    boolean isFirstAppRun();
}
