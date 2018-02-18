package pl.dogsinmylife.rest;

import io.reactivex.Observable;
import pl.dogsinmylife.models.BreedsResponse;
import retrofit2.http.GET;

import static pl.dogsinmylife.rest.Server.ALL_BREEDS;

public interface RestApi {

    @GET(ALL_BREEDS)
    Observable<BreedsResponse> getAllBreeds();
}
