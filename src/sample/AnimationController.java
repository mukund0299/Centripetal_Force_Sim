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
    Rectangle box2;

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

    @FXML
    Label positionLabel2;

    @FXML
    Label centForceLabel2;

    @FXML
    Label frictionForceLabel2;

    @FXML
    Label velocityLabel2;

    @FXML
    Label positionLabelMarker2;

    @FXML
    Label centForceLabelMarker2;

    @FXML
    Label frictionForceLabelMarker2;

    @FXML
    Label velocityLabelMarker2;

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
        if (DataRetrieval.getInstance().getMassSlider2().isDisable()) {
            //Making sure all the box2 stuff is invisible if box2 is not selected
            box2.setVisible(false);
            positionLabel2.setVisible(false);
            centForceLabel2.setVisible(false);
            frictionForceLabel2.setVisible(false);
            velocityLabel2.setVisible(false);
            positionLabelMarker2.setVisible(false);
            centForceLabelMarker2.setVisible(false);
            frictionForceLabelMarker2.setVisible(false);
            velocityLabelMarker2.setVisible(false);

            //Retrieving the data from the intermediary class
            double position = DataRetrieval.getInstance().getPositionSlider().getValue();
            double friction = DataRetrieval.getInstance().getFrictionSlider().getValue();
            double mass = DataRetrieval.getInstance().getMassSlider().getValue();
            String periodChosen = (DataRetrieval.getInstance().getPeriodBox().getValue());
            //The period in double form
            double period;

            if (periodChosen.equals("Fast")){
                period = fast;
            }
            else{
                period = slow;
            }

            //Setting the range for moving the box
            final double range = platform.getRadius();

            //Moving the box in position
            box.setY(platform.getCenterY() - (box.getHeight() / 2));
            box.setX((platform.getCenterX() + range * position) - box.getWidth());

            double normalForce = mass * 9.8;
            double frictionForce = normalForce * friction;
            //Moving the box around on the path
            Circle path = new Circle(platform.getCenterX(), platform.getCenterY(), (box.getX() + (box.getWidth() / 2)) - platform.getCenterX());
            double radiusCalc = path.getRadius() / 1000;
            double centForce = (mass * Math.pow(2 * Math.PI * radiusCalc, 2)) / radiusCalc;
            double velocity = 2 * Math.PI * radiusCalc / period;
            setLabels(position, friction, centForce, velocity, period, frictionForce, 0, 0, 0, 0);
            if (centForce < frictionForce) {
                PathTransition boxPath = new PathTransition();
                boxPath.setNode(box);
                boxPath.setDuration(Duration.seconds(period));
                boxPath.setPath(path);
                boxPath.setInterpolator(Interpolator.LINEAR);
                boxPath.setCycleCount(PathTransition.INDEFINITE);
                boxPath.play();
            } else {
                QuadCurve altPath = new QuadCurve(box.getX(), box.getY(), box.getX(), box.getY() + 100f, 150f, 550f);
                PathTransition alternatePath = new PathTransition();
                alternatePath.setNode(box);
                alternatePath.setDuration(Duration.seconds(period));
                alternatePath.setPath(altPath);
                alternatePath.setInterpolator(Interpolator.LINEAR);
                alternatePath.play();
            }
        }
        else{
            //Setting all the box2 data to be visible
            box2.setVisible(true);
            positionLabel2.setVisible(true);
            centForceLabel2.setVisible(true);
            frictionForceLabel2.setVisible(true);
            velocityLabel2.setVisible(true);
            box2.setVisible(true);
            positionLabelMarker2.setVisible(true);
            centForceLabelMarker2.setVisible(true);
            frictionForceLabelMarker2.setVisible(true);
            velocityLabelMarker2.setVisible(true);

            //Retrieving the data from the intermediary class
            double position = DataRetrieval.getInstance().getPositionSlider().getValue();
            double friction = DataRetrieval.getInstance().getFrictionSlider().getValue();
            double mass = DataRetrieval.getInstance().getMassSlider().getValue();
            String periodChosen = (DataRetrieval.getInstance().getPeriodBox().getValue());
            double position2 = DataRetrieval.getInstance().getPositionSlider2().getValue();
            double mass2 = DataRetrieval.getInstance().getMassSlider2().getValue();
            //The period in double form
            double period;

            if (periodChosen.equals("Fast")){
                period = fast;
            }
            else{
                period = slow;
            }

            //Setting the range for moving the box
            final double range = platform.getRadius();

            //Moving the box in position
            box.setY(platform.getCenterY() - (box2.getHeight() / 2));
            box.setX((platform.getCenterX() + range * position) - box2.getWidth());
            box2.setY(platform.getCenterY() - (box2.getHeight() / 2));
            box2.setX((platform.getCenterX() + range * position2) - box2.getWidth());

            double normalForce = mass * 9.8;
            double frictionForce = normalForce * friction;
            //Moving the box around on the path
            Circle path = new Circle(platform.getCenterX(), platform.getCenterY(), (box.getX() + (box.getWidth() / 2)) - platform.getCenterX());
            double radiusCalc = path.getRadius() / 1000;
            double centForce = (mass * Math.pow(2 * Math.PI * radiusCalc, 2)) / radiusCalc;
            double velocity = 2 * Math.PI * radiusCalc / period;

            double normalForce2 = mass2 * 9.8;
            double frictionForce2 = normalForce2*friction;
            Circle path2 = new Circle(platform.getCenterX(), platform.getCenterY(), (box2.getX() + (box2.getWidth() / 2)) - platform.getCenterX());
            double radiusCalc2 = path2.getRadius() / 1000;
            double centForce2 = (mass2 * Math.pow(2 * Math.PI * radiusCalc2, 2)) / radiusCalc2;
            double velocity2 = 2 * Math.PI * radiusCalc2 / period;

            setLabels(position, friction, centForce, velocity, period, frictionForce, position2, centForce2, velocity2, frictionForce2);

            //Box 1
            if (centForce < frictionForce) {
                PathTransition boxPath = new PathTransition();
                boxPath.setNode(box);
                boxPath.setDuration(Duration.seconds(period));
                boxPath.setPath(path);
                boxPath.setInterpolator(Interpolator.LINEAR);
                boxPath.setCycleCount(PathTransition.INDEFINITE);
                boxPath.play();
            } else {
                QuadCurve altPath = new QuadCurve(box.getX(), box.getY(), box.getX(), box.getY() + 100f, 150f, 550f);
                PathTransition alternatePath = new PathTransition();
                alternatePath.setNode(box);
                alternatePath.setDuration(Duration.seconds(period));
                alternatePath.setPath(altPath);
                alternatePath.setInterpolator(Interpolator.LINEAR);
                alternatePath.play();
            }

            //Box2
            if (centForce2 < frictionForce2) {
                PathTransition boxPath2 = new PathTransition();
                boxPath2.setNode(box2);
                boxPath2.setDuration(Duration.seconds(period));
                boxPath2.setPath(path2);
                boxPath2.setInterpolator(Interpolator.LINEAR);
                boxPath2.setCycleCount(PathTransition.INDEFINITE);
                boxPath2.play();
            } else {
                QuadCurve altPath2 = new QuadCurve(box2.getX(), box2.getY(), box2.getX(), box2.getY() + 100f, 150f, 550f);
                PathTransition alternatePath2 = new PathTransition();
                alternatePath2.setNode(box2);
                alternatePath2.setDuration(Duration.seconds(period));
                alternatePath2.setPath(altPath2);
                alternatePath2.setInterpolator(Interpolator.LINEAR);
                alternatePath2.play();
            }
        }
    }
    //Update all the labels on the side of the screen with the right values
    private void setLabels(double position, double friction, double centForce, double velocity, double period, double frictionForce, double position2, double centForce2, double velocity2, double frictionForce2){
        //Box 1
        positionLabel.setText(Integer.toString(Math.round((float)position)));
        frictionLabel.setText(Integer.toString(Math.round((float)friction)));
        centForceLabel.setText(Integer.toString(Math.round((float)centForce)) + " N");
        velocityLabel.setText(Double.toString(velocity));
        periodLabel.setText(Integer.toString(Math.round((float)period))+ " s");
        frictionForceLabel.setText(Integer.toString(Math.round((float)frictionForce)) + " N");

        //Box2
        positionLabel2.setText(Integer.toString(Math.round((float)position2)));
        centForceLabel2.setText(Integer.toString(Math.round((float)centForce2)) + " N");
        velocityLabel2.setText(Double.toString(velocity2));
        frictionForceLabel2.setText(Integer.toString(Math.round((float)frictionForce2)) + " N");
    }
}
