import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RonOS on 2/1/2017.
 */
public class averageThread extends Thread {
    private MySql sql = new MySql("145.33.225.143", 3306, "unwdmi", "zOlBAimnx9LlGsUw", "weathergen");
    private WeatherDatabaseHelper database = new WeatherDatabaseHelper(sql);

    public averageThread() {

    }

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
