package world.ucode.over;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import world.ucode.playfield.PlayField;
import world.ucode.window.ScreenController;

public class GameOver {

    @FXML
    private Label scoreLabel;

    @FXML
    public void initialize() {

    }

    @FXML
    private void handleGameOver() {
        ScreenController screenController = ScreenController.getScreenController();

        FXMLLoader ctrl = screenController.activate(ScreenController.GameScene.GAME_PLAY);
        PlayField field = ctrl.getController();
        field.startGame();
    }

    @FXML
    private void handleMainMenu() {
        ScreenController.getScreenController().activate(ScreenController.GameScene.MAIN_MENU);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

}
