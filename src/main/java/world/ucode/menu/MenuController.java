package world.ucode.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import world.ucode.playfield.PlayField;
import world.ucode.window.ScreenController;

public class MenuController {

    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button recordButton;

    @FXML
    public void initialize() {

    }

    @FXML
    private void handleExitButton(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();

        stage.close();
    }

    @FXML
    private void handleStartButton(ActionEvent e) {
        ScreenController screenController = ScreenController.getScreenController();

        FXMLLoader ctrl = screenController.activate(ScreenController.GameScene.GAME_PLAY);
        PlayField field = ctrl.getController();
        field.startGame();
    }

    @FXML
    private void handleRecordButton(ActionEvent e) {
    }
}
