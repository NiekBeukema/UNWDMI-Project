import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RonOS on 2/1/2017.
 */
public class averageThread extends Thread {
    private MySql sql = new MySql("145.33.225.143", 3306, "unwdmi", "zOlBAimnx9LlGsUw", "weathergen");
    private WeatherDatabaseHelper database = new WeatherDatabaseHelper(sql);

    /**
     * The average thread is responsible for updating the average values in the database
     */
    public averageThread() {

    }

    /**
     * This starts the thread and makes it calculate an updated average of all the measurements of the last 24 hours,
     * The update interval is set to 1 second by default.
     */
    public void run() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                database.insertAverageArgentinaData();
                database.insertAverageOceaniaData();
            }
        });
        timer.setRepeats(true); // Only execute once
        timer.start(); // Go go go!
    }
}
