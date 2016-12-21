package engine.path;

public class GridCoordinate implements Coordinate<Integer>{
    private int x;
    private int y;
    
    GridCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    GridCoordinate() {
        this(0, 0);
    }

    @Override
    public Integer getX () {
        return x;
    }

    @Override
    public void setX (Integer x) {
        this.x = x;
    }

    @Override
    public Integer getY () {
        return y;
    }

    @Override
    public void setY (Integer y) {
        this.y = y;
    }
    
    protected boolean isCardinalTo(Coordinate<Integer> neighbor) {
        return Math.abs(neighbor.getX() - x) + Math.abs(neighbor.getY() - y) == 1;     
    }
    
    @Override
    public boolean equals (Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof GridCoordinate)) {
            return false;
        }
        return (x == ((GridCoordinate) obj).getX()) && (y == ((GridCoordinate) obj).getY());
    }
    
}
