package ada.osc.myfirstweatherapp.ui.weatherFragment.presenter;

import ada.osc.myfirstweatherapp.networking.NetworkingHelper;
import ada.osc.myfirstweatherapp.networking.OnResponseListener;
import ada.osc.myfirstweatherapp.pojo.WeatherResponse;
import ada.osc.myfirstweatherapp.ui.weatherFragment.view.WeatherFragmentView;
import ada.osc.myfirstweatherapp.utils.Constants;

public class WeatherFragmentPresenterImpl implements WeatherFragmentPresenter, OnResponseListener<WeatherResponse> {

    private WeatherFragmentView view;
    private NetworkingHelper networkingHelper;

    public WeatherFragmentPresenterImpl(WeatherFragmentView view, NetworkingHelper networkingHelper) {
       setView(view);
       this.networkingHelper = networkingHelper;
    }

    @Override
    public double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }

    @Override
    public void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    view.setWeatherIcon(Constants.SNOW);
                    break;
                }
                case Constants.RAIN_CASE: {
                    view.setWeatherIcon(Constants.RAIN);
                    break;
                }
                case Constants.CLEAR_CASE: {
                    view.setWeatherIcon(Constants.SUN);
                    break;
                }
                case Constants.MIST_CASE: {
                    view.setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.FOG_CASE: {
                    view.setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.HAZE_CASE: {
                    view.setWeatherIcon(Constants.FOG);
                    break;
                }

                case Constants.CLOUD_CASE: {
                    view.setWeatherIcon(Constants.CLOUD);
                    break;
                }
            }

    }

    @Override
    public void setView(WeatherFragmentView view) {
        this.view=view;
    }
    @Override
    public void sendWeatherRequest(String cityToDisplay) {
        networkingHelper.requestWeatherFromAPI(cityToDisplay, this);
    }

    @Override
    public void onSuccess(WeatherResponse response) {
        view.setCurrentTemperatureValues(toCelsiusFromKelvin(response.getMain().getTemp_max()));
        view.setPressureValues(response.getMain().getPressure());
        view.setDescriptionValues(response.getWeatherObject().getDescription());
        createWeatherIconValue(response.getWeatherObject().getMain());
    }

    @Override
    public void onFailure(Throwable throwable) {
        view.onFailure();
    }

}
