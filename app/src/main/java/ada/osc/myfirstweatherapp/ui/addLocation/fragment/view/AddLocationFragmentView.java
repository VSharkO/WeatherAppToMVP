package ada.osc.myfirstweatherapp.ui.addLocation.fragment.view;

public interface AddLocationFragmentView {
    void onSuccess();
    void onLocationAlreadyExistsError();
    void onEmptyStringRequestError();

}
