package com.Journal.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class weatherService {

    private static final String API_Key = "ca3d9ee59db4c5cc920c576079f5c294";

    private static final String API = "https://api.weatherstack.com/current?access_key=APIKEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public String getWeather(String city){
        String finalAPI = API.replace("APIKEY", API_Key).replace("CITY", city);
        restTemplate.exchange(finalAPI, HttpMethod.GET,null,);
    }
}
