package it.unicam.pbparser.entities;

public class ParallelComponent {
    private int index;
    private int pair;
    private int occurrence;

    public ParallelComponent(int index, int pair, int occurrence) {
        this.index = index;
        this.pair = pair;
        this.occurrence = occurrence;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(short index) {
        this.index = index;
    }

    public int getPair() {
        return pair;
    }

    public void setPair(short pair) {
        this.pair = pair;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d);", this.index, this.pair, this.occurrence);
    }
}
