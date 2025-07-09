package com.jumedev.sialdo.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneUtils {

    public static void loadScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneUtils.class.getResource("/com/jumedev/sialdo/" + fxmlFile));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
