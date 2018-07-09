package ada.osc.myfirstweatherapp.ui.weatherFragment.presenter;

import ada.osc.myfirstweatherapp.ui.weatherFragment.view.WeatherFragmentView;

public interface WeatherFragmentPresenter {

    double toCelsiusFromKelvin(double temperature);
    void createWeatherIconValue(String description);
    void setView(WeatherFragmentView view);
    void sendWeatherRequest(String cityToDisplay);


}
