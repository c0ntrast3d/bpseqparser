package it.unicam.pbparser.entities;

public class ParallelComponent {
    private short index;
    private short pair;
    private short occurrence;

    public ParallelComponent(short index, short pair, short occurrence) {
        this.index = index;
        this.pair = pair;
        this.occurrence = occurrence;
    }

    public short getIndex() {
        return index;
    }

    public void setIndex(short index) {
        this.index = index;
    }

    public short getPair() {
        return pair;
    }

    public void setPair(short pair) {
        this.pair = pair;
    }

    public short getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(short occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", this.index, this.pair, this.occurrence);
    }
}
