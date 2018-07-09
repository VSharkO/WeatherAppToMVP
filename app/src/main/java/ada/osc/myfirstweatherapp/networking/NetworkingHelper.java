package ada.osc.myfirstweatherapp.networking;

import ada.osc.myfirstweatherapp.pojo.WeatherResponse;

public interface NetworkingHelper {
    void requestWeatherFromAPI(String city, OnResponseListener<WeatherResponse> listener);
}
