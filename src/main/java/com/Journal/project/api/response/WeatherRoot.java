package com.Journal.project.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter
@Setter
public class WeatherRoot{

    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;

        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;

        @JsonProperty("feelslke")
        private int feelsLike;
    }

}
