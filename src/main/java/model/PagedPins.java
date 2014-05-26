package model;

import java.util.List;

/**
 * Created by josh on 5/26/14.
 */
public class PagedPins<Pin> extends PagedList<Pin> {
    private List<Pin> pins;


    @Override
    public List<Pin> getWrapped() {
        return pins;
    }
}
