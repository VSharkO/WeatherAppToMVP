package ada.osc.myfirstweatherapp.ui.main.presenter;

import java.util.ArrayList;

import ada.osc.myfirstweatherapp.pojo.LocationWrapper;
import ada.osc.myfirstweatherapp.ui.main.view.MainActivityView;

public interface MainActivityPresenter {

    ArrayList<LocationWrapper> getLocations();
    void setView(MainActivityView view);
}
