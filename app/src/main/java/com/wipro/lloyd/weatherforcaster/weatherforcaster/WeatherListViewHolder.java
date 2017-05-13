package com.wipro.lloyd.weatherforcaster.weatherforcaster;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jithu on 5/12/2017.
 */

public class WeatherListViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_weather, tv_temp, tv_weather_detail, tv_day, tv_temp_morning, tv_temp_evening, tv_temp_night, tv_wind;
    public WeatherListViewHolder(View itemView) {
        super(itemView);
        tv_weather = (TextView) itemView.findViewById(R.id.tv_weather);
        tv_day = (TextView) itemView.findViewById(R.id.tv_day);
        tv_temp = (TextView) itemView.findViewById(R.id.tv_temp);
        tv_weather_detail = (TextView) itemView.findViewById(R.id.tv_weather_dis);
        tv_temp_morning = (TextView) itemView.findViewById(R.id.tv_temp_morning);
        tv_temp_evening = (TextView) itemView.findViewById(R.id.tv_temp_evening);
        tv_temp_night = (TextView) itemView.findViewById(R.id.tv_temp_night);
        tv_wind = (TextView) itemView.findViewById(R.id.tv_wind);
    }

    public TextView getTv_weather() {
        return tv_weather;
    }

    public TextView getTv_temp() {
        return tv_temp;
    }

    public TextView getTv_weather_detail() {
        return tv_weather_detail;
    }

    public TextView getTv_day() {
        return tv_day;
    }

    public TextView getTv_temp_morning() {
        return tv_temp_morning;
    }

    public TextView getTv_temp_evening() {
        return tv_temp_evening;
    }

    public TextView getTv_temp_night() {
        return tv_temp_night;
    }

    public TextView getTv_wind() {
        return tv_wind;
    }
}
