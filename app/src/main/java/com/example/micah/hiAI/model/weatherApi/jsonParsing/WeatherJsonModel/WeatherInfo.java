package com.example.micah.hiAI.model.weatherApi.jsonParsing.WeatherJsonModel;

import java.util.ArrayList;

public class WeatherInfo {

     public Coord coord;
     public ArrayList<WeatherTextInfo> weatherTextInfoArrayList;
     public String base;
     public WeatherStatsInfo main;
     public int visibility;
     public CloudData clouds;
     public long dt;
     public Sys sys;
     public int id;
     public String name;
     public int cod;
}
