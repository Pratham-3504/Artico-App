package com.Journal.project.service;

import com.Journal.project.api.response.WeatherRoot;
import com.Journal.project.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class weatherService {
    @Value("${weather.api.key}")
    private String API_Key;

    @Autowired
    private AppCache app_Cache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherRoot getWeather(String city){
        WeatherRoot weatherRoot = redisService.get(city, WeatherRoot.class);
        if(weatherRoot != null){
            return weatherRoot;
        }
        else{
            String finalAPI = app_Cache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<apikey>", API_Key).replace("<city>", city);
            ResponseEntity<WeatherRoot> body = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherRoot.class);
            WeatherRoot rootBody = body.getBody();
            if(rootBody != null){
                redisService.set(city,rootBody,300l);
                System.out.println("hello..");
            }
            return rootBody;
        }
    }
}

