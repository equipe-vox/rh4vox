module rh4vox.rh4vox {
    requires javafx.controls;
    requires javafx.fxml;
    // requires org.postgresql.jdbc;

    opens rh4vox.rh4vox to javafx.fxml;
    exports rh4vox.rh4vox;
}
