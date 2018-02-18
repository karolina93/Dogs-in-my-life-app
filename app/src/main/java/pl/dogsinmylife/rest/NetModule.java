package pl.dogsinmylife.rest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.dogsinmylife.App;
import pl.dogsinmylife.utils.NetworkUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    String mBaseUrl;
    Context context;

    public NetModule(String baseUrl, Context context) {
        this.mBaseUrl = baseUrl;
        this.context = context;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    OkHttpClient provideHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(new ConnectivityInterceptor(NetworkUtils.isNetworkAvailableObservable(context)));
        return builder.build();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    RestApi provideRestAPi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }
}


