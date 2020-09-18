package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import window.ScreenController;

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

        screenController.activate(ScreenController.GameScene.GAME_PLAY);
    }

    @FXML
    private void handleRecordButton(ActionEvent e) {
        System.out.println("record");
    }
}
