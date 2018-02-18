package pl.dogsinmylife;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import pl.dogsinmylife.rest.DaggerNetComponent;
import pl.dogsinmylife.rest.NetComponent;
import pl.dogsinmylife.rest.NetModule;
import pl.dogsinmylife.rest.Server;


public class App extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).build());

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Server.HOST, this))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
