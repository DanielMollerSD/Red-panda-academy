module com.example.redpandaacademy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;
    requires com.fazecast.jSerialComm;
    requires java.sql;


    opens com.example.redpandaacademy to javafx.fxml;
    exports com.example.redpandaacademy;
}