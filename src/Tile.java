
public enum Tile {

    WHITE(0), RED(1), YELLOW(2);
    private final int i;

    public int getI() {
        return i;
    }

    private Tile(int i) {
        this.i = i;
    }
}
