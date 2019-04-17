package it.unicam.pbparser;

public class BPair {
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private char baseName;
    private int pair;

    public char getBaseName() {
        return baseName;
    }

    void print() {
        System.out.println(String.format("Index: %s ::: BaseName: %s ::: PairIndex: %d", this.index, this.baseName, this.pair));
    }

    public void setBaseName(char baseName) {
        this.baseName = baseName;
    }

    int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
    }

    BPair(int index, char baseName, int pair) {
        this.index = index;
        this.baseName = baseName;
        this.pair = pair;
    }
}
