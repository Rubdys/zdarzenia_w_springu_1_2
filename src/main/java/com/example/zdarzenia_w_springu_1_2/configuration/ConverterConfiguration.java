package com.example.zdarzenia_w_springu_1_2.configuration;

import com.example.zdarzenia_w_springu_1_2.converter.CustomConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class ConverterConfiguration {

    @Bean
    public HttpMessageConverter<Object> customConverter(){
        return new CustomConverter();
    }

}
