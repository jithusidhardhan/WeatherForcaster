package com.wipro.lloyd.weatherforcaster.weatherforcaster;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * The class represents the adapter class for showing weather data
 * Created by Jithu on 5/13/2017.
 */

public class WeatherForcastAdapter extends RecyclerView.Adapter<WeatherListViewHolder> {
    private static final String UNIT_SPEED = " km/h";
    // the list of weather data
    private List<WeatherListItem> weatherList;
    // activity context
    private Context mContext;
    private String UNIT = " \u2103";

    /**
     * The constructor initialises the data
     * @param context Represents the context
     * @param weatherList Represents the weather list
     */
    public WeatherForcastAdapter(Context context, List<WeatherListItem> weatherList) {
        this.weatherList = weatherList;
        this.mContext = context;
    }

    /**
     * Android callback method used to create view holder
     * @param viewGroup the view group
     * @param i the view type
     * @return viewHolder The view holder
     */
    @Override
    public WeatherListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_list_item, null);
        WeatherListViewHolder viewHolder = new WeatherListViewHolder(view);
        return viewHolder;
    }

    /**
     * Android callback method used to bind holder with values
     * @param viewHolder the view holder
     * @param i the view type
     */
    @Override
    public void onBindViewHolder(WeatherListViewHolder viewHolder, int i) {
        //getting item from list
        WeatherListItem weatherListItem = weatherList.get(i);
        // binding values
        viewHolder.getTv_temp().setText(weatherListItem.getTemp()+UNIT);
        viewHolder.getTv_weather().setText(weatherListItem.getWeather());
        viewHolder.getTv_weather_detail().setText(weatherListItem.getWeather_details());
        viewHolder.getTv_day().setText(weatherListItem.getDayOfWeek());
        viewHolder.getTv_temp_morning().setText(weatherListItem.getMorningTemp()+UNIT);
        viewHolder.getTv_temp_night().setText(weatherListItem.getNightTemp()+UNIT);
        viewHolder.getTv_temp_evening().setText(weatherListItem.getEveningTemp()+UNIT);
        viewHolder.getTv_wind().setText(weatherListItem.getWind()+UNIT_SPEED);
    }

    /**
     * The method returns the item count
     * @return count number of items to be displayed in the list
     */
    @Override
    public int getItemCount() {
        return (null != weatherList ? weatherList.size() : 0);
    }

}