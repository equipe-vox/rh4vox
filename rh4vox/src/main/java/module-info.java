module br.com.rh4vox {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.com.rh4vox.controller to javafx.fxml;
    opens br.com.rh4vox.dao to java.sql;

    exports br.com.rh4vox;
    exports br.com.rh4vox.controller;
}