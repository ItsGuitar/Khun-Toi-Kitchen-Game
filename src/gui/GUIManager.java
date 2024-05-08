package gui;

import logic.GameController;

import javax.xml.crypto.Data;

public class GUIManager {
    private static DataPane dataPane;

    public static void init(){
        dataPane = new DataPane();
    }
    public static void update(){
        dataPane.update();
    }
    public static DataPane getDataPane(){
        return dataPane;
    }
}
