package com.example.gamingroom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            SQLConnection.makeConnection();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scene-builder.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            stage.setTitle("Sala de Gaming");
            stage.setScene(scene);
            stage.show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Conectare nereusita!");
            System.exit(0);
        }
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        SQLConnection.closeConenction();
        System.out.println("Deconectat!");
    }
    public static void main(String[] args) {
        launch();
    }
}