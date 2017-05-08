package sample;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anonymous on 4/24/17.
 */

public class AnimationController implements Initializable{
    @FXML
    Button backToInputBtn;

    @FXML
    Circle platform;

    @FXML
    Rectangle box;

    @FXML
    public void handleBackToInput(ActionEvent event) throws IOException{
        //Switching back to the input screen
        Parent myParent = FXMLLoader.load(getClass().getResource("inputScreen.fxml"));
        Scene newScene = new Scene(myParent);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(newScene);
        appStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Retrieving the data from the intermediary class
        double position = (DataRetrieval.getInstance().getPositionSlider().getValue());
        double friction = DataRetrieval.getInstance().getFrictionSlider().getValue();
        double mass = DataRetrieval.getInstance().getMassSlider().getValue();
        String period = DataRetrieval.getInstance().getPeriodBox().getValue();
        //Setting the range for moving the box
        final double range = platform.getRadius();

        //Moving the box in position
        box.setY(platform.getCenterY() - (box.getHeight() / 2));
        box.setX((platform.getCenterX() + range * position) - box.getWidth());

        if (period.equals("Fast")){
            double normalForce = mass*9.8;
            double frictionForce = normalForce*friction;
            //Moving the box around on the path
            Circle path = new Circle(platform.getCenterX(), platform.getCenterY(), box.getX()-platform.getCenterX());
            double centForce = (mass*(2*Math.PI*path.getRadius()))/path.getRadius();
            if (centForce > frictionForce) {
                PathTransition boxPath = new PathTransition();
                boxPath.setNode(box);
                boxPath.setDuration(Duration.seconds(2));
                boxPath.setPath(path);
                boxPath.setInterpolator(Interpolator.LINEAR);
                boxPath.setCycleCount(PathTransition.INDEFINITE);
                boxPath.play();
            }
            else{
                Line altPath = new Line(box.getX(), box.getY(), box.getX(), 0);
                PathTransition alternatePath = new PathTransition();
                alternatePath.setNode(box);
                alternatePath.setDuration(Duration.seconds(2));
                alternatePath.setPath(altPath);
                alternatePath.setInterpolator(Interpolator.LINEAR);
                alternatePath.setCycleCount(PathTransition.INDEFINITE);
                alternatePath.play();
            }
        }
    }
}
