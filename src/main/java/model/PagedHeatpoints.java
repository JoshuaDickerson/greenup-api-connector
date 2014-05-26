package model;

import java.util.List;

/**
 * Created by josh on 5/26/14.
 */
public class PagedHeatpoints<Heatpoint> extends PagedList<Heatpoint> {

    private List<Heatpoint> grid;

    public PagedHeatpoints(){}

    @Override
    public List<Heatpoint> getWrapped() {
        return grid;
    }


    public void setGrid(List<Heatpoint> grid) {
        this.grid = grid;
        super.setWrapped(grid);
    }
}
