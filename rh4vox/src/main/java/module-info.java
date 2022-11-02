module br.com.rh4vox {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.apache.commons.lang3;

    opens br.com.rh4vox.controller to javafx.fxml;
    opens br.com.rh4vox.dao to java.sql;

    exports br.com.rh4vox;
    exports br.com.rh4vox.controller;
    exports br.com.rh4vox.model;
}