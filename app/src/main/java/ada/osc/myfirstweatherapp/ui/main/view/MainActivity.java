package ada.osc.myfirstweatherapp.ui.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.ui.addNewLocation.view.AddNewLocationActivity;
import ada.osc.myfirstweatherapp.ui.adapters.CustomViewPagerFragmentAdapter;
import ada.osc.myfirstweatherapp.ui.main.presenter.MainActivityPresenter;
import ada.osc.myfirstweatherapp.ui.main.presenter.MainActivityPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private Toolbar mToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView;
    private ViewPager viewPager;
    private CustomViewPagerFragmentAdapter adapter;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initToolbar();

        presenter = new MainActivityPresenterImpl(this);
        adapter = new CustomViewPagerFragmentAdapter(getSupportFragmentManager());
        adapter.setAdapterData(presenter.getLocations());
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initNavigationDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        drawerLayout = findViewById(R.id.main_activity_drawer_layout);
        viewPager = findViewById(R.id.main_activity_view_pager);
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void initNavigationDrawer() {
        mNavigationView = findViewById(R.id.main_activity_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleItemSelectedClick(item.getItemId());
                return false;
            }
        });
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.main_activity_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void handleItemSelectedClick(int itemID) {
        switch (itemID) {
            case R.id.menu_add_new_location: {
                startActivity(new Intent(this, AddNewLocationActivity.class));
                drawerLayout.closeDrawers();
                break;
            }
        }
    }


}