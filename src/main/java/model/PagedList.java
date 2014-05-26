package model;

import java.util.List;

/**
 * Created by josh on 5/26/14.
 * wrapper class for holding a paged list of objects returned from the API
 */
public abstract class PagedList<T> {
    private int statusCode;
    private Page page;
    private List<T> wrapped;

    public PagedList(){}

    public abstract List<T> getWrapped();

    public void setWrapped(List<T> wrapped) {
        this.wrapped = wrapped;
    }

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

}
