package pl.dogsinmylife.rest;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    private boolean isNetworkActive;

    public ConnectivityInterceptor(Observable<Boolean> isNetworkActive) {
        isNetworkActive.subscribe(_isNetworkActive -> this.isNetworkActive = _isNetworkActive);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        if (!isNetworkActive) {
            throw new NoConnectivityException();
        } else {
            return chain.proceed(chain.request());
        }
    }
}