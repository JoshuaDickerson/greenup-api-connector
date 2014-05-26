package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static model.Constants.*;

/**
 * Created by josh on 5/26/14.
 */
public class Comment {
    private COMMENT_TYPE type;
    private String message;
    private String timestamp;
    private Date date;
    private String pin;
    private Long id;
    private boolean addressed;

    public Comment(){}

    public Comment(COMMENT_TYPE type, String message, String timestamp, String pinId, Long id){
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
        this.pin = pinId;
        this.id = id;
        this.date = formatDate(timestamp);
    }

    public COMMENT_TYPE getType() {
        return type;
    }

    public void setType(COMMENT_TYPE type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.date = formatDate(timestamp);
        this.timestamp = timestamp;
    }

    public Date getDate() {
        if(this.date == null){
            return formatDate(timestamp);
        }
        return date;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pinId) {
        this.pin = pinId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAddressed() {
        return addressed;
    }

    public void setAddressed(boolean addressed) {
        this.addressed = addressed;
    }

    private Date formatDate(String dateString){
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (message != null ? !message.equals(comment.message) : comment.message != null) return false;
        if (pin != null ? !pin.equals(comment.pin) : comment.pin != null) return false;
        if (timestamp != null ? !timestamp.equals(comment.timestamp) : comment.timestamp != null) return false;
        if (type != comment.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
