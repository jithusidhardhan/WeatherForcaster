package com.wipro.lloyd.weatherforcaster.weatherforcaster;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The class implements WeatherForcastPresenter which manages data for WeatherForcast view
 * Created by Jithu on 5/13/2017.
 */

public class StandardWeatherForcastPresenter implements WeatherForcastPresenter {
    /*Volley request que*/
    private RequestQueue queue;
    /**
     * Instance of presenter
     */
    private WeatherForcast weatherForcast;
    /**
     * Constant used to manage Temperature
     */
    private double KELVIN_CONSTANT = 273.15;
    // Activity context
    private Context activityContext;

    /**
     * Constructor initializes the presenter
     * @param activityContext Represents activity context
     * @param weatherForcast Represents instance of realization of WeatherForcast
     */
    StandardWeatherForcastPresenter(Context activityContext, WeatherForcast weatherForcast) {
        this.activityContext = activityContext;
        queue = Volley.newRequestQueue(activityContext);
        this.weatherForcast = weatherForcast;
    }

    /**
     * Fetches the weather data using Volley library
     * @param url The given url
     */
    @Override
    public void fetchWeatherCast(String url) {

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<WeatherListItem> weatherDetails = parseWeatherResult(response);
                            weatherForcast.updateWeatherView(weatherDetails);
                        } catch (JSONException e) {
                            weatherForcast.showError(activityContext.getResources().getString(R.string.parse_error));
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        weatherForcast.showError(activityContext.getResources().getString(R.string.network_error));
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }

    /**
     * Method to parse response and create model data
     * @param response The response received from server
     * @return weatherDetails The list of WeatherListItem, which is the weather data
     * @throws JSONException The json exception while parsing
     */
    private List<WeatherListItem> parseWeatherResult(JSONObject response) throws JSONException {
        List<WeatherListItem> weatherDetails = new ArrayList<WeatherListItem>();
        if(null != response) {
            // Checking response code
            if(response.getInt("cod") == 200) {
                JSONArray listJson = response.getJSONArray("list");
                // parsing array
                for (int i = 0; i < listJson.length(); i++) {
                    JSONObject dayItems = listJson.getJSONObject(i);
                    WeatherListItem weatherListItem = new WeatherListItem();
                    // parsing date
                    if(dayItems.has("dt")) {
                       String dayOfWeek = getDayOfWeek(dayItems.getLong("dt"), i);
                       weatherListItem.setDayOfWeek(dayOfWeek);
                    }

                    // PARSING TEMP
                    if(dayItems.has("temp")) {
                        JSONObject temp = dayItems.getJSONObject("temp");
                        // day temp
                        if(temp.has("day")) {
                            double day = temp.getDouble("day");
                            weatherListItem.setTemp(day-KELVIN_CONSTANT);
                        }
                        // night temp
                        if(temp.has("night")) {
                            double night = temp.getDouble("night");
                            weatherListItem.setNightTemp(night-KELVIN_CONSTANT);
                        }
                        // evening temp
                        if(temp.has("eve")) {
                            double eve = temp.getDouble("eve");
                            weatherListItem.setEveningTemp(eve-KELVIN_CONSTANT);
                        }
                        // Morning temp
                        if(temp.has("morn")) {
                            double morn = temp.getDouble("morn");
                            weatherListItem.setMorningTemp(morn-KELVIN_CONSTANT);
                        }
                    }
                    if(dayItems.has("speed")) {
                        double speed = dayItems.getDouble("speed");
                        weatherListItem.setWind(speed);
                    }
                    // parsing weather
                    if(dayItems.has("weather")) {
                        JSONArray weatherList = dayItems.getJSONArray("weather");
                        JSONObject weather = weatherList.getJSONObject(0);
                        // title
                        if(weather.has("main")) {
                            weatherListItem.setWeather(weather.getString("main"));
                        }
                        // description
                        if(weather.has("description")) {
                            weatherListItem.setWeather_details(weather.getString("description"));
                        }
                    }
                    weatherDetails.add(weatherListItem);
                }
            }
        }
        return weatherDetails;
    }

    /**
     * The method returns the display Day of week
     * @param dateTime given timestamp
     * @param i position in list
     * @return dayOfWeek to be displayed
     */
    private String getDayOfWeek(long dateTime, int i) {
        switch (i) {
            case 0:
                return "Today";
            case 1:
                return "Tomorrow";
            default:
                Date date = new Date (dateTime * 1000);
                DateFormat formatDay = new SimpleDateFormat("EEE");
                return formatDay.format(date);
        }
    }

}
