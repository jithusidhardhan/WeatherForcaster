package com.wipro.lloyd.weatherforcaster.weatherforcaster;

import java.util.List;

/**
 * The interface provides methods to interact with the activity
 * Created by Jithu on 5/13/2017.
 */

public interface WeatherForcast {
    /**
     * The method is used to update View with the weather data
     * @param weatherdetails the list of weather data
     */
    void updateWeatherView(List<WeatherListItem> weatherdetails);
    /**
     * The method shows the error info
     * @param message The message to be shown
     */
    void showError(String message);
}
