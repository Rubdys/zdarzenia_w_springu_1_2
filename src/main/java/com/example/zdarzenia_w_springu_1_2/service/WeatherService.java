package com.example.zdarzenia_w_springu_1_2.service;

import com.example.zdarzenia_w_springu_1_2.model.WeatherDTO;
import com.example.zdarzenia_w_springu_1_2.proxy.IWeatherForecast;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WeatherService {

    private final IWeatherForecast weatherForecast;

    public WeatherService(IWeatherForecast weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public WeatherDTO getWeatherForecast() {
        long start = System.currentTimeMillis();
        if(new Random().nextInt(5) == 0){
            weatherForecast.refreshData();
        }
        String weather = weatherForecast.getWeather();
        long end = System.currentTimeMillis();
        System.out.println("The execution took: " + (end - start) + " [ms]");
        return new WeatherDTO(weather);
    }
}
