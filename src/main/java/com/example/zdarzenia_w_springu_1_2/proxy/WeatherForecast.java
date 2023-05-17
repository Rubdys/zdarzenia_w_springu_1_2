package com.example.zdarzenia_w_springu_1_2.proxy;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class WeatherForecast implements IWeatherForecast {
    private String currentWeather;

    public WeatherForecast() {
        refreshData();
    }

    public void refreshData(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            log.error(e.getMessage());
        }
        currentWeather = generateWeather();
    }

    private String generateWeather(){
        int random = new Random().nextInt(5);
        switch (random){
            case 0:
                return "Sunny";
            case 1:
                return "Rainy";
            case 2:
                return "Cloudy";
            case 3:
                return "Storm";
            case 4:
                return "Snowy";
        }
        return null;
    }

    public String getWeather() {
        return currentWeather;
    }
}
