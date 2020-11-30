
public enum Tile {

    RED(1), YELLOW(2), WHITE(0);
    private final int i;

    public int getI() {
        return i;
    }

    private Tile(int i) {
        this.i = i;
    }
}
