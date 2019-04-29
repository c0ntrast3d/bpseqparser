package it.unicam.pbparser.entities;

public class BPair {
    private int index;

    public int getIndex() {
        return index;
    }

    private char baseName;
    private int pair;

    public int getPair() {
        return pair;
    }

    public int realIndex() {
        return this.pair - 1;
    }

    public void mark() {
        this.pair = -1;
    }

    public boolean pairNotNull() {
        return this.pair != 0 && this.pair != -1;
    }

    public BPair(int index, char baseName, int pair) {
        this.index = index;
        this.baseName = baseName;
        this.pair = pair;
    }
}
