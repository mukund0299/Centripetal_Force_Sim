package sample;

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

    private Slider positionSlider;
    private Slider frictionSlider;
    private ChoiceBox<String> periodBox;
    private Slider massSlider;

    public Slider getPositionSlider() {
        return positionSlider;
    }

    public void setPositionSlider(Slider positionSlider) {
        this.positionSlider = positionSlider;
    }

    public Slider getFrictionSlider() {
        return frictionSlider;
    }

    public void setFrictionSlider(Slider frictionSlider) {
        this.frictionSlider = frictionSlider;
    }

    public ChoiceBox<String> getPeriodBox() {
        return periodBox;
    }

    public void setPeriodBox(ChoiceBox<String> periodBox) {
        this.periodBox = periodBox;
    }

    public Slider getMassSlider() {
        return massSlider;
    }

    public void setMassSlider(Slider massSlider) {
        this.massSlider = massSlider;
    }
}
