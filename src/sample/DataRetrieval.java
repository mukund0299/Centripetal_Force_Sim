package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

/**
 * Created by anonymous on 4/24/17.
 */
public class DataRetrieval {
    private static DataRetrieval instance = new DataRetrieval();

    public static DataRetrieval getInstance() {
        return instance;
    }

    private JFXSlider positionSlider;
    private JFXSlider frictionSlider;
    private JFXComboBox<String> periodBox;
    private JFXSlider massSlider;

    public JFXSlider getPositionSlider() {
        return positionSlider;
    }

    public void setPositionSlider(JFXSlider positionSlider) {
        this.positionSlider = positionSlider;
    }

    public JFXSlider getFrictionSlider() {
        return frictionSlider;
    }

    public void setFrictionSlider(JFXSlider frictionSlider) {
        this.frictionSlider = frictionSlider;
    }

    public JFXComboBox<String> getPeriodBox() {
        return periodBox;
    }

    public void setPeriodBox(JFXComboBox<String> periodBox) {
        this.periodBox = periodBox;
    }

    public JFXSlider getMassSlider() {
        return massSlider;
    }

    public void setMassSlider(JFXSlider massSlider) {
        this.massSlider = massSlider;
    }
}
