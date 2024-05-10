package gui;

import logic.GameController;

import javax.xml.crypto.Data;
import java.sql.Time;

public class GUIManager {
    private static DataPane dataPane;
    private static TimerPane timerPane;
    private static KitchenDataPane kitchenDataPane;

    public static void init(){
        timerPane = TimerPane.getInstance();
        kitchenDataPane = new KitchenDataPane();
        dataPane = new DataPane();
    }
    public static void update(){

        timerPane.update();
        kitchenDataPane.update();
        dataPane.update();

    }
    public static DataPane getDataPane(){
        return dataPane;
    }
    public static TimerPane getTimerPane(){
        return timerPane;
    }
    public static KitchenDataPane getKitchenDataPane(){
        return kitchenDataPane;
    }
}
