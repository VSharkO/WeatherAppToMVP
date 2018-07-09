package ada.osc.myfirstweatherapp.ui.addLocation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.pojo.LocationWrapper;
import ada.osc.myfirstweatherapp.model.Model;
import ada.osc.myfirstweatherapp.ui.main.view.MainActivity;
import ada.osc.myfirstweatherapp.utils.Constants;

/**
 * Created by Filip on 10/02/2016.
 */

//TODO jos new location napraviti mvp i gg

public class AddLocationFragment extends Fragment implements View.OnClickListener {
    private EditText mEnterLocationNameEditText;
    private Button mAddLocationButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        getActivity().startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();

    }

    public void onLocationAlreadyExistsError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.location_already_exists_error_message));
    }

    public void onEmptyStringRequestError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.empty_location_string_error_message));
    }

    private void initUI(View view) {
        mEnterLocationNameEditText = (EditText) view.findViewById(R.id.fragment_add_location_enter_city_edit_text);
        mAddLocationButton = (Button) view.findViewById(R.id.fragment_add_location_button);
        mAddLocationButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mAddLocationButton) {
            String userInput = mEnterLocationNameEditText.getText().toString();
            if(!userInput.equals("")){
                LocationWrapper location = new LocationWrapper(userInput);
                if(!Model.getInstance().getStringLocations().contains(location.getLocation())){
                    Model.getInstance().add(location);
                    onSuccess();
                }else{
                    onLocationAlreadyExistsError();
                }
            }else{
                onEmptyStringRequestError();
            }
        }
    }

    public static AddLocationFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        AddLocationFragment f = new AddLocationFragment();
        f.setArguments(data);
        return f;
    }
}
