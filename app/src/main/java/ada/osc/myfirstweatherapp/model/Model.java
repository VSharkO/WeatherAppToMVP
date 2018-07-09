package ada.osc.myfirstweatherapp.model;

import java.util.ArrayList;

import ada.osc.myfirstweatherapp.pojo.LocationWrapper;


public class Model {
    private static Model model;

    private Model() {
    }

    private ArrayList<LocationWrapper> locations = new ArrayList<>();

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
            return model;

        } else {
            return model;
        }
    }

    public void add(LocationWrapper location) {
        locations.add(location);
    }

    public ArrayList<LocationWrapper> getLocations() {
        return locations;
    }

    public ArrayList<String> getStringLocations() {
        ArrayList<String> list = new ArrayList<>();
        for (LocationWrapper location : locations) {
            list.add(location.getLocation());
        }
        return list;
    }
}
