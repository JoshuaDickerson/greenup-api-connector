package model;

import java.util.Date;

import static model.Constants.*;

/**
 * Created by josh on 5/26/14.
 */
public class Comment {
    private final COMMENT_TYPE type;
    private final String message;
    private final Date timestamp;
    private final String pinId;
    private final Long id;

    public Comment(COMMENT_TYPE type, String message, Date timestamp, String pinId, Long id){
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
        this.pinId = pinId;
        this.id = id;
    }

    public COMMENT_TYPE getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getPinId() {
        return pinId;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (message != null ? !message.equals(comment.message) : comment.message != null) return false;
        if (pinId != null ? !pinId.equals(comment.pinId) : comment.pinId != null) return false;
        if (timestamp != null ? !timestamp.equals(comment.timestamp) : comment.timestamp != null) return false;
        if (type != comment.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (pinId != null ? pinId.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
