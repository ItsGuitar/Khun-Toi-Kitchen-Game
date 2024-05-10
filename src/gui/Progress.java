package gui;

import javafx.scene.control.ProgressBar;

public class Progress {
    ProgressBar progressBar;
    public Progress(){
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);
        progressBar.setPrefHeight(20);
        //progressBar.setProgress(0);

        //testin
        progressBar.setProgress(0.5);
    }
}
