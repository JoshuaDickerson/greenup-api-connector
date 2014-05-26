package model;

import java.util.List;

/**
 * Created by josh on 5/26/14.
 * wrapper class for holding a paged list of objects returned from the API
 */
public class PagedList<T> {
    private String previousPage = null;
    private String nextPage = null;
    private List<T> wrappedList;

}
