package model;

import java.util.List;

/**
 * Created by josh on 5/26/14.
 */
public class PagedComments<Comment> extends PagedList<Comment> {
    private List<Comment> comments;

    public PagedComments(){}

    @Override
    public List<Comment> getWrapped() {
        return comments;
    }


    public void setComments(List<Comment> comments) {
        this.comments = comments;
        super.setWrapped(comments);
    }
}
