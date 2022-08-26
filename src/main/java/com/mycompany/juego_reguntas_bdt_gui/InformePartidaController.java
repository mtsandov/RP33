
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.modelo.Partida;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InformePartidaController implements Initializable {

    @FXML
    private TableView<String> tablaInforme;
    @FXML
    private TableColumn<String, String> preguntas;

    static Queue<String> preguntasCola = new LinkedList<>();
    static boolean win = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preguntas.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        while (preguntasCola.peek() != null) {
            tablaInforme.getItems().add(preguntasCola.poll());
        }
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("pierdePartida");
        for (String s : tablaInforme.getItems()) {
            System.out.println(s);
            preguntasCola.add(s);

        }
        if (win == true) {
            App.setRoot("ganaPartida");
        } else {
            App.setRoot("pierdePartida");
        }
    }

    @FXML
    private void salir(ActionEvent event) throws IOException {
        App.setRoot("InicioJuego");
        App.resetearIntentosUsuario();
        win = false;

    }

}
