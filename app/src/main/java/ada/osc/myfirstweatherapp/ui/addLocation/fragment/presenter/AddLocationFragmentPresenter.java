package ada.osc.myfirstweatherapp.ui.addLocation.fragment.presenter;

import ada.osc.myfirstweatherapp.ui.addLocation.fragment.view.AddLocationFragment;

public interface AddLocationFragmentPresenter {

    void setView(AddLocationFragment view);
    void addNewLocation(String location);

}
