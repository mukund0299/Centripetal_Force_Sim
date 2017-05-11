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
import javafx.scene.control.Label;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.geom.Arc2D;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anonymous on 4/24/17.
 */

public class AnimationController implements Initializable{
    @FXML
    Circle platform;

    @FXML
    Rectangle box;

    @FXML
    Label positionLabel;

    @FXML
    Label frictionLabel;

    @FXML
    Label centForceLabel;

    @FXML
    Label velocityLabel;

    @FXML
    Label periodLabel;

    @FXML
    Label frictionForceLabel;

    //setting the time of rotation per period option
    final double fast = 3;
    final double slow = 6;

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
        double position = DataRetrieval.getInstance().getPositionSlider().getValue();
        double friction = DataRetrieval.getInstance().getFrictionSlider().getValue();
        double mass = DataRetrieval.getInstance().getMassSlider().getValue();
        String period = DataRetrieval.getInstance().getPeriodBox().getValue();
        //Setting the range for moving the box
        final double range = platform.getRadius();

        //Moving the box in position
        box.setY(platform.getCenterY() - (box.getHeight() / 2));
        box.setX((platform.getCenterX() + range * position) - box.getWidth());

        if (period.equals("Fast")) {
            double normalForce = mass * 9.8;
            double frictionForce = normalForce * friction;
            //Moving the box around on the path
            Circle path = new Circle(platform.getCenterX(), platform.getCenterY(), box.getX() - platform.getCenterX());
            double centForce = (mass * (2 * Math.PI * path.getRadius())) / path.getRadius();
            double velocity = (2*Math.PI*path.getRadius())/fast;
            setLabels(position, friction, centForce, velocity, fast, frictionForce);
            if (centForce > frictionForce) {
                PathTransition boxPath = new PathTransition();
                boxPath.setNode(box);
                boxPath.setDuration(Duration.seconds(fast));
                boxPath.setPath(path);
                boxPath.setInterpolator(Interpolator.LINEAR);
                boxPath.setCycleCount(PathTransition.INDEFINITE);
                boxPath.play();
            } else {
                ArcTo altPath = new ArcTo(box.getX(), box.getY(), 50f, 50f);
                PathTransition alternatePath = new PathTransition();
                alternatePath.setNode(box);
                alternatePath.setDuration(Duration.seconds(fast));
                alternatePath.setPath(altPath);
                alternatePath.setInterpolator(Interpolator.LINEAR);
                alternatePath.setCycleCount(PathTransition.INDEFINITE);
                alternatePath.play();
            }
        }
        else{
            double normalForce = mass * 9.8;
            double frictionForce = normalForce * friction;
            //Moving the box around on the path
            Circle path = new Circle(platform.getCenterX(), platform.getCenterY(), box.getX() - platform.getCenterX());
            double centForce = (mass * (2 * Math.PI * path.getRadius())) / path.getRadius();
            double velocity = (2*Math.PI*path.getRadius())/slow;
            System.out.println("centForce = " + centForce);
            System.out.println("frictionForce = " + frictionForce);
            setLabels(position, friction, centForce, velocity, slow, frictionForce);
            if (centForce > frictionForce) {
                PathTransition boxPath = new PathTransition();
                boxPath.setNode(box);
                boxPath.setDuration(Duration.seconds(slow));
                boxPath.setPath(path);
                boxPath.setInterpolator(Interpolator.LINEAR);
                boxPath.setCycleCount(PathTransition.INDEFINITE);
                boxPath.play();
            } else {
                Line altPath = new Line(box.getX(), box.getY(), box.getX(), 0);
                PathTransition alternatePath = new PathTransition();
                alternatePath.setNode(box);
                alternatePath.setDuration(Duration.seconds(fast));
                alternatePath.setPath(altPath);
                alternatePath.setInterpolator(Interpolator.LINEAR);
                alternatePath.setCycleCount(PathTransition.INDEFINITE);
                alternatePath.play();
            }
        }
    }
    //Update all the labels on the side of the screen with the right values
    public void setLabels(double position, double friction, double centForce, double velocity, double period, double frictionForce){
        positionLabel.setText(Double.toString(position));
        frictionLabel.setText(Double.toString(friction));
        centForceLabel.setText(Double.toString(centForce));
        velocityLabel.setText(Double.toString(velocity));
        periodLabel.setText(Double.toString(period));
        frictionLabel.setText(Double.toString(frictionForce));
    }
}
