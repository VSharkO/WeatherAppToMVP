package ada.osc.myfirstweatherapp.ui.main.view;

import android.view.MenuItem;

public interface MainActivityView {

    boolean onOptionsItemSelected(MenuItem item);

    void handleItemSelectedClick(int itemID);
}
