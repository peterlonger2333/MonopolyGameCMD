package hk.edu.polyu.comp3211.g27.model.square;

public class Square {
    private final int index;
    private final String label;

    public Square(int index, String label) {
        this.index = index;
        this.label = label;
    }

    public int getIndex() {
        return index;
    }

    public String getLabel() {
        return label;
    }
}
