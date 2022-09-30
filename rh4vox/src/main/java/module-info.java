module br.com.rh4vox {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.rh4vox.controller to javafx.fxml;
    
    exports br.com.rh4vox;
    exports br.com.rh4vox.controller;
}