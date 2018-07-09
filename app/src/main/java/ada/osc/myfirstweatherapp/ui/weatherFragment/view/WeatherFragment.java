package ada.osc.myfirstweatherapp.ui.weatherFragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.ui.weatherFragment.presenter.WeatherFragmentPresenter;
import ada.osc.myfirstweatherapp.ui.weatherFragment.presenter.WeatherFragmentPresenterImpl;
import ada.osc.myfirstweatherapp.utils.Constants;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragment extends Fragment implements WeatherFragmentView {

    private TextView mCurrentTemperature;
    private TextView mMinTemperature;
    private TextView mMaxTemperature;
    private TextView mPressure;
    private TextView mWind;
    private TextView mDescription;
    private ImageView mWeatherIcon;

    private WeatherFragmentPresenter presenter;

    public static WeatherFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        WeatherFragment f = new WeatherFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initPresenter();
    }

    private void initPresenter() {
        presenter = new WeatherFragmentPresenterImpl(this, App.getInstance().getNetworkingHelper());
    }

    @Override
    public void onStart() {
        super.onStart();
        String cityToDisplay = getArguments().getString(Constants.CITY_BUNDLE_KEY);
        presenter.sendWeatherRequest(cityToDisplay);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshCurrentData();
    }

    private void initUI(View view) {
        mCurrentTemperature = view.findViewById(R.id.weather_display_current_temperature_text_view);
        mMinTemperature = view.findViewById(R.id.weather_fragment_min_temperature_text_view);
        mMaxTemperature = view.findViewById(R.id.weather_fragment_max_temperature_text_view);
        mPressure = view.findViewById(R.id.weather_display_pressure_text_view);
        mWind = view.findViewById(R.id.weather_display_wind_text_view);
        mDescription = view.findViewById(R.id.weather_display_detailed_description_text_view);
        mWeatherIcon = view.findViewById(R.id.weather_display_weather_icon_image_view);
    }
    @Override
    public void setCurrentTemperatureValues(double temperatureValues) {
        mCurrentTemperature.setText(getString(R.string.current_temperature_message, temperatureValues));
    }
    @Override
    public void setMinTemperatureValues(double minTemperatureValues) {
        mMinTemperature.setText(getString(R.string.minimum_temperature_message, minTemperatureValues));
    }
    @Override
    public void setMaxTemperatureValues(double maxTemperatureValues) {
        mMaxTemperature.setText(getString(R.string.maximum_temperature_message, maxTemperatureValues));
    }
    @Override
    public void setPressureValues(double pressureValues) {
        mPressure.setText(getString(R.string.pressure_message, pressureValues));

    }
    @Override
    public void setWindValues(double windValues) {
        mWind.setText(getString(R.string.wind_speed_message, windValues));
    }
    @Override
    public void setWeatherIcon(String iconPath) {
        Glide.with(getActivity().getApplicationContext()).load(Constants.IMAGE_BASE_URL + iconPath).into(mWeatherIcon);
    }
    @Override
    public void setDescriptionValues(String descriptionValues) {
        mDescription.setText(descriptionValues);
    }

    @Override
    public void onFailure() {
        Toast.makeText(App.getInstance(), R.string.weather_fragment_loading_failure_toast_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshCurrentData() {
        presenter.sendWeatherRequest(this.getArguments().getString(Constants.CITY_BUNDLE_KEY));
    }
}
