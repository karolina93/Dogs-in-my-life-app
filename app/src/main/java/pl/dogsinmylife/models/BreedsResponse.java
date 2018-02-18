package pl.dogsinmylife.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BreedsResponse {
    String status;
    transient List<Breed> breedList;

    @SerializedName("message")
    List<String> namesList;

    public String getStatus() {
        return status;
    }

    public List<Breed> getBreedList() {
        breedList = new ArrayList<>();
        for (String s : namesList) {
            Breed breed = new Breed(s);
            breed.setLiked(true);
            breedList.add(breed);
        }
        return breedList;
    }
}
