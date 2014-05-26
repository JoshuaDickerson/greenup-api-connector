package model;

/**
 * Created by josh on 5/26/14.
 */
public class Pin {
    private long id;
    private float latDegrees;
    private float lonDegrees;
    private Constants.COMMENT_TYPE type;
    private String message;
    private boolean addressed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Constants.COMMENT_TYPE getType() {
        return type;
    }

    public void setType(Constants.COMMENT_TYPE type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAddressed() {
        return addressed;
    }

    public void setAddressed(boolean addressed) {
        this.addressed = addressed;
    }
}
