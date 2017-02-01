import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public void insertOceaniaData(int stationId, Float cloudcoverage, String date) {
        mySql.insertQuery("INSERT INTO oceania (date, stationId, cloudcoverage) VALUES('" + date + "', " + stationId + ", " + cloudcoverage.toString() + ")");
    }

    public void insertArgentinaData(int stationId, Float cloudcoverage, Float visibility, String date) {
        mySql.insertQuery("INSERT INTO argentina (date, stationId, visibility, cloudcoverage) VALUES('" +  date + "', "  + stationId + ", " + visibility.toString() + ", " + cloudcoverage.toString() + ")");
    }

    public void insertAverageOceaniaData() {
        ResultSet set = mySql.getSelectFromQuery("SELECT date, cloudcoverage FROM oceania WHERE date between date_sub(now(),INTERVAL 1 DAY) AND now()");
        int counter = 0;
        float total = 0.0f;
        try {
            while (set.next()) {
                total += set.getFloat(2);
                counter++;
            }
        }



        catch (java.sql.SQLException ex) {
            ex.toString();
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date today = Calendar.getInstance().getTime();
        String date = df.format(today);
        float averageCloudCoverage = total / counter;
        mySql.insertQuery("INSERT INTO oceaniaaverage(date, cloudcoverage) VALUES('" + date + "',  " + averageCloudCoverage + ")");
    }

    public void insertAverageArgentinaData() {
        ResultSet set = mySql.getSelectFromQuery("SELECT date, cloudcoverage, visibility FROM argentina where date between date_sub(now(),INTERVAL 1 DAY) and now()");
        int counter = 0;
        float totalcloudcoverage = 0.0f;
        float totalvisibility = 0.0f;
        try {
            while (set.next()) {
                totalcloudcoverage += set.getFloat(2);
                totalvisibility += set.getFloat(3);
                counter++;
            }
        }

        catch (java.sql.SQLException ex) {

        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date today = Calendar.getInstance().getTime();
        String date = df.format(today);
        float averageCloudCoverage = totalcloudcoverage / counter;
        float averageVisibilty = totalvisibility / counter;
        mySql.insertQuery("INSERT INTO argentinaaverage(date, cloudcoverage, visibility) VALUES('" + date + "',  " + averageCloudCoverage + ", " + averageVisibilty + ")");
    }

    public float getAverageVisibilty(int stationId, String tablename){
        ResultSet result = mySql.getSelectFromQuery("SELECT * FROM " + tablename +  " WHERE stationId = " + stationId);
        float total = 0.0f;
        int counter = 0;

        try {
            while(result.next()){
                total += result.getFloat("visibility");
                counter ++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total / counter;
    }

    public float getAverageCloudCoverage(int stationId, String tablename){
        ResultSet result = mySql.getSelectFromQuery("SELECT * FROM " + tablename +  " WHERE stationId = " + stationId);
        float total = 0.0f;
        int counter = 0;

        try {
            while(result.next()){
                total += result.getFloat("cloudcoverage");
                counter ++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total / counter;
    }



    public void purgeDataBase() {
        System.out.println("Purging database");
        mySql.delete("argentina", "", true);
        mySql.delete("oceania", "", true);
    }
}