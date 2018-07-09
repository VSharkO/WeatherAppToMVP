package ada.osc.myfirstweatherapp;

import android.app.Application;
import android.support.annotation.NonNull;

import ada.osc.myfirstweatherapp.networking.ApiService;
import ada.osc.myfirstweatherapp.networking.NetworkingHelper;
import ada.osc.myfirstweatherapp.networking.NetworkingHelperImpl;
import ada.osc.myfirstweatherapp.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Filip on 01/04/2016.
 */

//TODO add database cacheing
public class App extends Application {

    private static App sInstance;
    private Retrofit retrofit;
    private NetworkingHelper networkingHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        retrofit = provideRestClient();

        ApiService service = createWeatherAPIService(retrofit);

        networkingHelper = new NetworkingHelperImpl(service);
    }

    private ApiService createWeatherAPIService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    public static App getInstance() {
        return sInstance;
    }

    @NonNull
    private Retrofit provideRestClient() {
        return new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public NetworkingHelper getNetworkingHelper(){
        return networkingHelper;
    }
}
