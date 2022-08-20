module com.mycompany.juego_reguntas_bdt_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.juego_reguntas_bdt_gui to javafx.fxml;
    exports com.mycompany.juego_reguntas_bdt_gui;
}
