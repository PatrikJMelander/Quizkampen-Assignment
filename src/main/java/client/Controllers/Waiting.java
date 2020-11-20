package client.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Patrik Melander
 * Date: 2020-11-19
 * Time: 16:02
 * Project: Quiskampen
 * Copyright: MIT
 */
public class Waiting implements Initializable, Runnable{
    Thread thread = new Thread(this);
    ScreenNavigator s = new ScreenNavigator();
    ObjectInputStream input;
    ObjectOutputStream output;
    Object temp;

    @FXML
    private ImageView image;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input = ScreenNavigator.inputStreamer;
        output = ScreenNavigator.outputStreamer;
        System.out.println("Waiting");
        thread.start();

    }

    @Override
    public void run() {

        try {
            output.writeObject("Waiting");
            String s2;
            while((s2 = input.readObject().toString())!=null) {
                if (s2.endsWith("QUESTION")) {
                    //output.writeObject("HEJ");
                    System.out.println("Båda i waiting");
                    break;
                    //thread.interrupt();
                }
            }
            s.loadNewScreen(ScreenNavigator.GAME_VIEW, image);
            thread.interrupt();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}