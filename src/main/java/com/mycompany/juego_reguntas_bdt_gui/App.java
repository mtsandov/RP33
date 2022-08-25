package com.mycompany.juego_reguntas_bdt_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Alert;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("InicioJuego"), 1080, 720);
        scene.getStylesheets().add(App.class.getResource("css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void showAlert(Alert.AlertType tipo, String mensaje) {
    Alert alert = new Alert(tipo);

    alert.setTitle("Resultado de operacion");
    alert.setHeaderText("Notificacion");
    alert.setContentText(mensaje);
    alert.showAndWait();
    }

}