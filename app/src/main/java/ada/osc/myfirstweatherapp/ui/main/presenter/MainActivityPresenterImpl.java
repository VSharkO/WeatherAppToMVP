package ada.osc.myfirstweatherapp.ui.main.presenter;


import java.util.ArrayList;
import ada.osc.myfirstweatherapp.model.Model;
import ada.osc.myfirstweatherapp.pojo.LocationWrapper;
import ada.osc.myfirstweatherapp.ui.main.presenter.MainActivityPresenter;
import ada.osc.myfirstweatherapp.ui.main.view.MainActivityView;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private Model model;
    private MainActivityView view;

    public MainActivityPresenterImpl(MainActivityView view){
        model = Model.getInstance();
        setView(view);
    }

    @Override
    public void setView(MainActivityView view) {
        this.view=view;
    }

    @Override
    public ArrayList<LocationWrapper> getLocations() {
        return model.getLocations();
    }


}
