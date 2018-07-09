package ada.osc.myfirstweatherapp.ui.weatherFragment.view;

public interface WeatherFragmentView {
    void setCurrentTemperatureValues(double temperatureValues);
    void setMinTemperatureValues(double minTemperatureValues);
    void setMaxTemperatureValues(double maxTemperatureValues);
    void setPressureValues(double pressureValues);
    void setWindValues(double windValues);
    void setWeatherIcon(String iconPath);
    void refreshCurrentData();
    void setDescriptionValues(String descriptionValues);
    void onFailure();
}
