package model;

/**
 * Created by josh on 5/26/14.
 */
public class Heatpoint {
    private float latDegrees;
    private float lonDegrees;
    private int secondsWorked;

    public Heatpoint(){}

    public float getLatDegrees() {
        return latDegrees;
    }

    public void setLatDegrees(float latDegrees) {
        this.latDegrees = latDegrees;
    }

    public float getLonDegrees() {
        return lonDegrees;
    }

    public void setLonDegrees(float lonDegrees) {
        this.lonDegrees = lonDegrees;
    }

    public int getSecondsWorked() {
        return secondsWorked;
    }

    public void setSecondsWorked(int secondsWorked) {
        this.secondsWorked = secondsWorked;
    }
}
