import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RonOS on 2/1/2017.
 */
public class averageThread extends Thread {
    private MySql sql = new MySql("127.0.0.1", 3306, "iica", "", "root");
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
