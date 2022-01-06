module com.example.gamingroom {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.oracle.database.jdbc;
    requires java.sql;
    requires java.desktop;

    opens com.example.gamingroom to javafx.fxml;
    exports com.example.gamingroom;
    exports ClaseDate;
    opens ClaseDate to javafx.fxml;
}