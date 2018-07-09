package ada.osc.myfirstweatherapp.ui.addLocation.fragment.presenter;


import ada.osc.myfirstweatherapp.model.Model;
import ada.osc.myfirstweatherapp.pojo.LocationWrapper;
import ada.osc.myfirstweatherapp.ui.addLocation.fragment.view.AddLocationFragment;

public class AddLocationFragmentPresenterImpl implements AddLocationFragmentPresenter {

    private AddLocationFragment view;
    private Model model;

    public AddLocationFragmentPresenterImpl(AddLocationFragment view) {
        setView(view);
        model = Model.getInstance();
    }

    @Override
    public void setView(AddLocationFragment view) {
        this.view = view;
    }

    @Override
    public void addNewLocation(String location) {
            if(!location.equals("")){
                LocationWrapper locationWrapper = new LocationWrapper(location);
                if(!model.getStringLocations().contains(locationWrapper.getLocation())){
                    model.add(locationWrapper);
                    view.onSuccess();
                }else{
                    view.onLocationAlreadyExistsError();
                }
            }else{
                view.onEmptyStringRequestError();
            }
    }
}
