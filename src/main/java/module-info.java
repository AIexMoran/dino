module world.ucode {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    exports world.ucode.main;
    exports world.ucode.playfield;
    exports world.ucode.menu;
    exports world.ucode.over;
    exports world.ucode.window;
    exports world.ucode.utils;
    exports world.ucode.playfield.object;
}