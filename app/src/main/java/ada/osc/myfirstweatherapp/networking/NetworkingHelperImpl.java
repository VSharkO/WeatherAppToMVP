package ada.osc.myfirstweatherapp.networking;

import ada.osc.myfirstweatherapp.pojo.WeatherResponse;
import ada.osc.myfirstweatherapp.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkingHelperImpl implements NetworkingHelper {

    private ApiService service;

    public NetworkingHelperImpl(ApiService service) {
        this.service = service;
    }

    @Override
    public void requestWeatherFromAPI(final String city, final OnResponseListener<WeatherResponse> listener) {
        service.getWeather(Constants.APP_ID, city).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response != null && response.body() != null) {
                    WeatherResponse data = response.body();
                    if(data != null)
                    data.setCityName(city);
                    listener.onSuccess(data);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
