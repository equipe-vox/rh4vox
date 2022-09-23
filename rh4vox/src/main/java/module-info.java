module rh4vox.rh4vox {
    requires javafx.controls;
    requires javafx.fxml;

    opens rh4vox.rh4vox.controller to javafx.fxml;
    exports rh4vox.rh4vox.controller;
}
