package ada.osc.myfirstweatherapp.ui.addLocation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.ui.addLocation.fragment.view.AddLocationFragment;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddNewLocationActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        initFragment();
        initToolbar();
    }

    private void initFragment() {
        if (getSupportFragmentManager().findFragmentById(R.id.add_location_activity_frame_layout) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_location_activity_frame_layout,new AddLocationFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(R.string.add_location_activity_title);
        }
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
