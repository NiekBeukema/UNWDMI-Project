
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void insertOceaniaData(int stationId, Float cloudcoverage) {
        mySql.insertQuery("INSERT INTO oceania (stationId, cloudcoverage) VALUES(" + stationId + ", " + cloudcoverage.toString() + ")");
    }

    public void insertArgentinaData(int stationId, Float cloudcoverage, Float visibility) {
        mySql.insertQuery("INSERT INTO argentina (stationId, visibility, cloudcoverage) VALUES(" + stationId + ", " + visibility.toString() + ", " + cloudcoverage.toString() + ")");
    }

    public float getAverage(int stationId, String fieldName){
        ResultSet result = mySql.getSelectFromQuery("SELECT * FROM argentina WHERE stationId = " + stationId);
        float total = 0.0f;
        int counter = 0;
        //IK WIL PUSHEN


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



    public void purgeDataBase() {
        System.out.println("Purging database");
        mySql.delete("station", "", true);
        mySql.delete("weatherdata", "", true);
    }
}
