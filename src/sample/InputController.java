package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InputController implements Initializable{

    //Controls
    @FXML
    JFXSlider positionInput;

    @FXML
    JFXSlider frictionInput;

    @FXML
    JFXComboBox<String> periodInput;

    @FXML
    JFXSlider massInput;

    @FXML
    JFXSlider massInput2;

    @FXML
    JFXSlider positionInput2;

    @FXML
    public void handleCreateAnimation(ActionEvent event) throws IOException{
        //Setting the data to this class in order to retrieve it in the next window
        DataRetrieval.getInstance().setFrictionSlider(frictionInput);
        DataRetrieval.getInstance().setPositionSlider(positionInput);
        DataRetrieval.getInstance().setPeriodBox(periodInput);
        DataRetrieval.getInstance().setMassSlider(massInput);
        DataRetrieval.getInstance().setMassSlider2(massInput2);
        DataRetrieval.getInstance().setPositionSlider2(positionInput2);

        //Creating a new window to show the animation
        Parent myParent = FXMLLoader.load(getClass().getResource("animationScreen.fxml"));
        Scene newScene = new Scene(myParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(newScene);
        appStage.show();
    }

    @FXML
    public void handleActivateBox(ActionEvent event) throws IOException{
        massInput2.setDisable(false);
        positionInput2.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        periodInput.getItems().addAll("Fast", "Slow");
        periodInput.getSelectionModel().selectFirst();
    }
}
