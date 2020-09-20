module dino.main {
    requires javafx.controls;
    requires javafx.fxml;

    opens world.ucode.main to javafx.fxml;
    exports world.ucode.main;
}