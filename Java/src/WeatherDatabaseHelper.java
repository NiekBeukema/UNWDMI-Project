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

    /**
     * This class makes handling project specific database operations easier for the programmer
     * @param mysql
     */
    public WeatherDatabaseHelper(MySql mysql) {
        this.mySql = mysql;
    }

    /**
     * This query allows the insertion of Oceania's weatherdata
     * @param stationId The station id from which the measurement originated
     * @param cloudcoverage The measured cloudcoverage
     * @param date The date of the measurement
     */
    public void insertOceaniaData(int stationId, Float cloudcoverage, String date) {
        mySql.insertQuery("INSERT INTO oceania (date, stationId, cloudcoverage) VALUES('" + date + "', " + stationId + ", " + cloudcoverage.toString() + ")");
    }

    /**
     * This query allows the insertion of Argentina's weatherdata
     * @param stationId The station id from which the measurement originated
     * @param cloudcoverage The measured cloudcoverage
     * @param visibility The measured visibility
     * @param date The date of the measurement
     */
    public void insertArgentinaData(int stationId, Float cloudcoverage, Float visibility, String date) {
        mySql.insertQuery("INSERT INTO argentina (date, stationId, visibility, cloudcoverage) VALUES('" +  date + "', "  + stationId + ", " + visibility.toString() + ", " + cloudcoverage.toString() + ")");
    }

    /**
     * This method calculates the average value of the last 24 hours of measurements
     * and inserts it into the average database for Oceania
     */
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

    /**
     * This method calculates the average value of the last 24 hours of measurements
     * and inserts it into the average database for Argentina
     */
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

    /**
     * This method returns the average visibility for a given station
     * @param stationId The station from which the average visibility must be calculated
     * @param tablename The tablename from where the data must originate
     * @return Returns the average in floating point format
     */
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

    /**
     * This method returns the average cloudcoverage for a given station
     * @param stationId The station from which the average cloudcoverage must be calculated
     * @param tablename The tablename from where the data must originate
     * @return Returns the average in floating point format
     */
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
}