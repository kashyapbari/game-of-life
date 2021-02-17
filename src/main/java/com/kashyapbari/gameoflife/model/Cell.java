package com.kashyapbari.gameoflife.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Cell {
    private int row;
    private int column;

    private static final Integer[] NEIGHBOUR_MATRIX = {-1,0,1};

    public Cell(String location){
        String[] positions = location.strip().split(",");
        this.row = Integer.parseInt(positions[0].strip());
        this.column = Integer.parseInt(positions[1].strip());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return getRow() == cell.getRow() && getColumn() == cell.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }

    public Set<Cell> getNeighbours() {
        Set<Cell> neighbours = new Set<>(new HashSet<>());
        for(int row: NEIGHBOUR_MATRIX){
            for (int col: NEIGHBOUR_MATRIX){
                if (!(row == 0 && col == 0)){
                    neighbours.add(new Cell(this.row+row, this.column+col));
                }
            }
        }
        return neighbours;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
