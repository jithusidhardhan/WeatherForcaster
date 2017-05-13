package com.wipro.lloyd.weatherforcaster.weatherforcaster;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents the model class for binding and displaying data
 * Created by Jithu on 5/12/2017.
 */

public class WeatherListItem {
    // It represents the temparature
    private double temp;
    // It represents the temparature at night
    private double nightTemp;
    // It represents the temparature at evening
    private double eveningTemp;
    // It represents the temparature at morning
    private double morningTemp;
    // It represents the weather title
    private String weather;
    // It represents the weather details
    private String weather_details;
    // It represents the day of week
    private String dayOfWeek;
    // It represents wind
    private double wind;
    // It represents the float round limit
    private static final int ROUND_LIM = 2;

    /**
     * The method returns temp
     * @return temp The temperature
     */
    public double getTemp() {
        return round(temp, ROUND_LIM);
    }

    /**
     * The method sets the temp
     * @param temp The temperature
     */
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * The method returns the weather title
     * @return weather weather title
     */
    public String getWeather() {
        return weather;
    }

    /**
     * The method sets the weather title
     * @param weather weather title
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }
    /**
     * The method returns the weather description
     * @return weather_details weather description
     */
    public String getWeather_details() {
        return weather_details;
    }
    /**
     * The method sets the weather description
     * @param weather_details weather description
     */
    public void setWeather_details(String weather_details) {
        this.weather_details = weather_details;
    }
    /**
     * The method returns the night temp
     * @return nightTemp The temp at night
     */
    public double getNightTemp() {
        return round(nightTemp, ROUND_LIM);
    }

    /**
     * The method sets the night temp
     * @param nightTemp temp at night
     */
    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    /**
     * The method returns the morning temp
     * @return morningTemp morning temp
     */
    public double getMorningTemp() {
        return round(morningTemp, ROUND_LIM);
    }
    /**
     * The method sets the morning temp
     * @param morningTemp temp at morning
     */
    public void setMorningTemp(double morningTemp) {
        this.morningTemp = morningTemp;
    }

    /**
     * The method sets evening temp
     * @param eveTemp the evening temp
     */
    public void setEveningTemp(double eveTemp) {
        this.eveningTemp = eveTemp;
    }

    /**
     * The method returns eve temp
     * @return eveningTemp eve temp
     */
    public double getEveningTemp() {
        return round(eveningTemp, ROUND_LIM);
    }

    /**
     * The method returns day of week
     * @return dayOfWeek The day of week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * The method sets the day of week
     * @param dayOfWeek The day of week
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Method to get wind
     * @return wind wind data
     */
    public double getWind() {
        return wind;
    }

    /**
     * Method to set wind
     * @param wind wind data
     */
    public void setWind(double wind) {
        this.wind = wind;
    }

    /**
     * The method rounds value to given places
     * @param value the double value
     * @param places number of places to be round to
     * @return rounded value
     */
    public static double round(double value, int places) {
        if (places < 0) {
            return 0;
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
