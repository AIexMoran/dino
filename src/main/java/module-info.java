module world.ucode {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens world.ucode.menu to javafx.fxml;
    opens world.ucode.playfield to javafx.fxml;
    opens world.ucode.over to javafx.fxml;

    exports world.ucode.main;
    exports world.ucode.playfield.object;
    exports world.ucode.playfield;
    exports world.ucode.menu;
    exports world.ucode.over;
    exports world.ucode.utils;
    exports world.ucode.window;
}