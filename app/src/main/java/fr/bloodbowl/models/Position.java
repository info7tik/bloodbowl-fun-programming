package fr.bloodbowl.models;

import java.util.Objects;

import lombok.Getter;

public class Position {
    @Getter
    private final int row;
    @Getter
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isAdjacent(Position anotherPosition) {
        int rowDifference = Math.abs(getRow() - anotherPosition.getRow());
        int columnDifference = Math.abs(getColumn() - anotherPosition.getColumn());
        if (rowDifference > 1 || columnDifference > 1) {
            return false;
        }
        if (rowDifference + columnDifference == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position that = (Position) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "[" + row + ", " + column + "]";
    }
}
