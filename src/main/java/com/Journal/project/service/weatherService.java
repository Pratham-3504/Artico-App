package com.Journal.project.service;

import com.Journal.project.api.response.WeatherRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weatherService {

    private static final String API_Key = "ca3d9ee59db4c5cc920c576079f5c294";

    private static final String API = "https://api.weatherstack.com/current?access_key=APIKEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherRoot getWeather(String city){
        String finalAPI = API.replace("APIKEY", API_Key).replace("CITY", city);
        ResponseEntity<WeatherRoot> body = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherRoot.class);
        WeatherRoot rootBody = body.getBody();
        return rootBody;
    }
}

