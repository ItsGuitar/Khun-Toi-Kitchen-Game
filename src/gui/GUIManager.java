package gui;

import logic.GameController;

import javax.xml.crypto.Data;
import java.sql.Time;

public class GUIManager {
    private static DataPane dataPane;
    private static TimerPane timerPane;

    public static void init(){
        dataPane = new DataPane();
        timerPane = TimerPane.getInstance();
    }
    public static void update(){
        dataPane.update();
        timerPane.update();
    }
    public static DataPane getDataPane(){
        return dataPane;
    }
    public static TimerPane getTimerPane(){
        return timerPane;
    }
}
