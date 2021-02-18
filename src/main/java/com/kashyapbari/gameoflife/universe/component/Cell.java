package com.kashyapbari.gameoflife.universe.component;

import com.kashyapbari.gameoflife.container.Set;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;

@AllArgsConstructor
@Builder
@ToString
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
        return row == cell.row && column == cell.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
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

    public String stringValue(){
        return row+","+column;
    }
}
