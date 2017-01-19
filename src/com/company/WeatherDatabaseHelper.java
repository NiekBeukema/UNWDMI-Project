package com.company;

import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * Created by RonOS on 16-12-2016.
 */
public class WeatherDatabaseHelper {
    MySql mySql;

    public WeatherDatabaseHelper(MySql mysql) {
        this.mySql = mysql;
    }

    public void insertWeahterStation(String name, String location) {
        mySql.insertQuery("INSERT INTO Station (Name, Location) VALUES('" + name + "', '" + location + "')");

    }

    public void insertWeahterData(int stationID, String time, float temperature, float dewPoint, float airPressure, int visibility,
                                  float rainfall, float snowDepth, float cloudiness, String windDirection, float windVelocity, String occurences) {
        mySql.insertQuery("INSERT INTO weatherdata (Station_id, Time, Temperature, DewPoint, AirPressure," +
                "Visibility, Rainfall, SnowDepth, Cloudiness, WindDirection," +
                "WindVelocity, Occurings) VALUES(" + stationID + ",'" + time + "'," + temperature + ", " + dewPoint  + ", " +
                airPressure + ", " + visibility + ", " + rainfall + ", " + snowDepth + ", " + cloudiness + ", '" +
                windDirection + "', " + windVelocity + ", '" + occurences + "') ");

    }

    public void purgeDataBase() {
        System.out.println("Purging database");
        mySql.delete("station", "", true);
        mySql.delete("weatherdata", "", true);
    }
}
