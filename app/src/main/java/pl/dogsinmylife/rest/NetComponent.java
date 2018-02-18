package pl.dogsinmylife.rest;

import javax.inject.Singleton;

import dagger.Component;
import pl.dogsinmylife.AppModule;
import pl.dogsinmylife.breed.BreedActivity;


@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(BreedActivity activity);
}