

import com.sun.javafx.geom.AreaOp;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // write your code here
        benchDatabase(1000);


    }

    public static void benchDatabase(int records) {
        String anim= "|/-\\";
        WeatherDatabaseHelper helper = new WeatherDatabaseHelper(new MySql("localhost", 3306, "unwdmi", "", "root"));
        helper.purgeDataBase();
        String date = "2016-06-23 12:00:00";
        Date time = new Date();
        for (int x=0; x <= records; x++) {
            try {

                helper.insertWeahterData(0,date , 21.0f, 12.0f, 300.0f, 100, 0.0f, 0.0f, 0.0f, "North-East", 10.0f, "None");
                String data = "\r" + anim.charAt(x % anim.length())  + " " + x + " Records inserted " ;
                System.out.write(data.getBytes());
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }

        Date timedifference = new Date();
        int diffInMilliSeconds = (int) ( (timedifference.getTime() - time.getTime()));
        float msPerSecond = (float) diffInMilliSeconds / records;

        System.out.print("in " + diffInMilliSeconds + "ms, ");
        System.out.print(msPerSecond + " ms per record");
    }



}
