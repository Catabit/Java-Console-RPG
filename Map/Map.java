package main.Map;

public class Map {
    private int lines, cols;
    private MapTile[][] tiles;

    public final MapTile[][] getTiles() {
        return tiles;
    }

    public Map(final int inLines, final int inCols) {
        this.lines = inLines;
        this.cols = inCols;
        this.tiles = new MapTile[lines][cols];
    }

    /**
     * Used to get a MapTile with an offset to the current tile.
     * @param crPos the current position from which to compute the offset
     * @param xOffset the x axis offset (vertical/lines)
     * @param yOffset the y axis offset (horizontal/columns)
     * @return the offseted position
     */
    public final MapTile getTileOffset(final MapTile crPos, final int xOffset, final int yOffset) {
        return tiles[crPos.getX() + xOffset][crPos.getY() + yOffset];
    }
}
