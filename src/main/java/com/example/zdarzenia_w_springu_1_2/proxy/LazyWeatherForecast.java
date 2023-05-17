package com.example.zdarzenia_w_springu_1_2.proxy;

import org.springframework.stereotype.Component;

@Component
public class LazyWeatherForecast implements IWeatherForecast{

    private WeatherForecast weatherForecast;

    @Override
    public String getWeather() {
        initializeWeatherForecast();
        return weatherForecast.getWeather();
    }

    @Override
    public void refreshData() {
        initializeWeatherForecast();
        weatherForecast.refreshData();
    }

    private void initializeWeatherForecast(){
        if(weatherForecast == null){
            weatherForecast = new WeatherForecast();
        }
    }
}
