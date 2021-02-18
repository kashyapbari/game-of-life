package com.kashyapbari.gameoflife;

import com.kashyapbari.gameoflife.model.Cell;
import com.kashyapbari.gameoflife.model.Set;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
public class Universe {
    Set<Cell> alive;

    public Universe(HashSet<Cell> alive) {
        this.alive = new Set<>(alive);
    }

    public Universe(String aliveMatrix) {
        this(cellSetFromString(aliveMatrix));
    }

    private static HashSet<Cell>  cellSetFromString(String cellMatrix){
        HashSet<Cell> cells = new HashSet();
        for(String location: cellMatrix.split("\n+")){
            cells.add(new Cell(location));
        }
        return cells;
    }

    public void playTick(int tick){
        for (int i = 0; i < tick; i++) {
            Set<Cell> dead = new Set<>(new HashSet<>());
            Set<Cell> killed = new Set<>(new HashSet<>());
            Set<Cell> resurrected = new Set<>(new HashSet<>());
            alive.getSet().forEach(cell ->
                playAliveCell(cell, dead, killed));
            dead.getSet().forEach(cell ->
                    playDeadCell(cell, resurrected));
            alive.removeAll(killed);
            alive.addAll(resurrected);
        }
    }

    private void playAliveCell(Cell cell, Set<Cell> dead, Set<Cell> killed){
        Set<Cell> neighbours = cell.getNeighbours();
        killRule(cell, getAliveNeighbourSize(neighbours), killed);
        neighbours.removeAll(alive);
        dead.addAll(neighbours);
    }

    private void playDeadCell(Cell cell, Set<Cell> resurrected){
        resurrectRule(cell, getAliveNeighbourSize(cell.getNeighbours()), resurrected);

    }

    private Integer getAliveNeighbourSize(Set<Cell> neighbours){
        return neighbours.intersection(alive).size();
    }

    private void killRule(Cell cell, Integer size, Set<Cell> killed){
        if (size < 2 || size > 3){
            killed.add(cell);
        }
    }

    private void resurrectRule(Cell cell, Integer size,  Set<Cell> resurrected){
        if (size == 3){
            resurrected.add(cell);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Universe universe = (Universe) o;
        return Objects.equals(getAlive(), universe.getAlive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAlive());
    }

    @Override
    public String toString() {
        return "Universe{" +
                "alive=" + alive +
                '}';
    }
}
