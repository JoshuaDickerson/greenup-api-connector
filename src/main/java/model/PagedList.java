package model;

import java.util.List;

/**
 * Created by josh on 5/26/14.
 * wrapper class for holding a paged list of objects returned from the API
 */
public class PagedList<T> {
    private List<T> comments;
    private int statusCode;
    private Page page;

    public PagedList(){}

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<T> getComments() {
        return comments;
    }

    public void setComments(List<T> comments) {
        this.comments = comments;
    }
}
