package com.wipro.lloyd.weatherforcaster.weatherforcaster;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

/**
 * The Activity class contains the views of Weather cast application
 */
public class WeatherForcastActivity extends AppCompatActivity implements WeatherForcast, AdapterView.OnItemSelectedListener {
    // It represents the Presenter
    private WeatherForcastPresenter weatherForcastPresenter;
    // It represents the recycler view instance
    private RecyclerView recycler_view;
    // It represents the spinner instance
    private Spinner locationSpinner;
    // It represents the list of ids for particular locations
    private List<Integer> locationIds;
    // It represents the list of short listed locations
    private List<String> locations;
    // It represents the main url to which the request is send to
    private String MAIN_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    // It represents the sub url contains the app id and other required info
    private String SUB_URL = "&APPID=65a44cb4307d544a1174ed51b792ab95&cnt=5";
    // It represents the instance of snackbar
    private Snackbar errorSnackbar;

    /**
     * The android callback method to create the view
     * @param savedInstanceState the saved instance state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);
        weatherForcastPresenter = new StandardWeatherForcastPresenter(this, this);
        initViews();
        initData();
        initListners();

    }

    /**
     * The method contains binding listners
     */
    private void initListners() {
        locationSpinner.setOnItemSelectedListener(this);
    }
    /**
     * The method contains initialisation of data
     */
    private void initData() {
        locations = new ArrayList<String>();
        locationIds = new ArrayList<Integer>();
        locationIds.add(2635167);
        locations.add("Select Location");
        locations.add("United Kingdom of Great Britain and Northern Ireland");
        locationIds.add(6269131);
        locations.add("England");
        locationIds.add(2652355);
        locations.add("Cornwall");
        locationIds.add(2638741);
        locations.add("Saint Martins Green");
        // spinner adapter
        ArrayAdapter spinnerdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locations );
        spinnerdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(spinnerdapter);
    }
    /**
     * The method contains initialisation of views
     */
    private void initViews() {
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        errorSnackbar = Snackbar.make(locationSpinner, "", Snackbar.LENGTH_LONG);
    }

    /**
     * The method update view with the weather details
     * @param weatherdetails the list of weather data
     */
    @Override
    public void updateWeatherView(List<WeatherListItem> weatherdetails) {
        String test = weatherdetails.get(0).getWeather();
        WeatherForcastAdapter adapter = new WeatherForcastAdapter(WeatherForcastActivity.this, weatherdetails);
        recycler_view.setAdapter(adapter);
    }

    /**
     * The android call back method for selecting spinner item
     * @param parent Parent view
     * @param view The spinnet view
     * @param position selected position
     * @param id the view id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position > 0) {
            int locationId = locationIds.get(position-1);
            // requesting to fetch data
            weatherForcastPresenter.fetchWeatherCast(MAIN_URL+"id="+locationId+SUB_URL);
        }
    }

    /**
     * The android callback method when no item selected
     * @param parent Parent view
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * The method shows the error info
     * @param message The message to be shown
     */
    public void showError(String message) {
        errorSnackbar.setText(message);
        errorSnackbar.show();
        locationSpinner.setSelection(0);
    }

}
