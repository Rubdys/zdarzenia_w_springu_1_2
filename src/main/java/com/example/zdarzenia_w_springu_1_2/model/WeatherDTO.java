package com.example.zdarzenia_w_springu_1_2.model;

public class WeatherDTO {
    private String weatherForecast;

    public WeatherDTO(String weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public String getWeatherForecast() {
        return weatherForecast;
    }
}
