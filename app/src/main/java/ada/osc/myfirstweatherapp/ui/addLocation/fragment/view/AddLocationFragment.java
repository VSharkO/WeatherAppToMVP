package ada.osc.myfirstweatherapp.ui.addLocation.fragment.view;

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
import ada.osc.myfirstweatherapp.ui.addLocation.fragment.presenter.AddLocationFragmentPresenter;
import ada.osc.myfirstweatherapp.ui.addLocation.fragment.presenter.AddLocationFragmentPresenterImpl;
import ada.osc.myfirstweatherapp.ui.main.view.MainActivity;

/**
 * Created by Filip on 10/02/2016.
 */

public class AddLocationFragment extends Fragment implements AddLocationFragmentView,View.OnClickListener {
    private EditText mEnterLocationNameEditText;
    private Button mAddLocationButton;

    private AddLocationFragmentPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        getActivity().startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();

    }
    @Override
    public void onLocationAlreadyExistsError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.location_already_exists_error_message));
    }

    @Override
    public void onEmptyStringRequestError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.empty_location_string_error_message));
    }

    private void initUI(View view) {
        mEnterLocationNameEditText = view.findViewById(R.id.fragment_add_location_enter_city_edit_text);
        mAddLocationButton = view.findViewById(R.id.fragment_add_location_button);
        mAddLocationButton.setOnClickListener(this);
    }

    private void initPresenter(){
        presenter = new AddLocationFragmentPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAddLocationButton) {
            presenter.addNewLocation(mEnterLocationNameEditText.getText().toString());
        }
    }

}
