package com.wipro.lloyd.weatherforcaster.weatherforcaster;

/**
 * The interface provides the methods to communicate with the presenter
 * Created by Jithu on 5/13/2017.
 */

public interface WeatherForcastPresenter {
    /**
     * The method fetches data from given URL
     * @param url the given URL
     */
    void fetchWeatherCast(String url);
}
