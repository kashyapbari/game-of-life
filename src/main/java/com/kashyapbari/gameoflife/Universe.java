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
    Set<Cell> dead;
    Set<Cell> killed;
    Set<Cell> resurrected;

    public Universe(HashSet<Cell> alive) {
        this.alive = new Set<>(alive);
        this.dead = new Set<>(new HashSet<>());
        this.killed = new Set<>(new HashSet<>());
        this.resurrected = new Set<>(new HashSet<>());
    }

    public Universe(String aliveMatrix) {
        this(cellSetFromString(aliveMatrix));
    }

    private static HashSet<Cell>  cellSetFromString(String cellMatrix){
        HashSet<Cell> cells = new HashSet();
        for(String location: cellMatrix.split("\n+")){
//            String[] positions = location.strip().split(",");
//            cells.add(new Cell(
//                    Integer.parseInt(positions[0].strip()),
//                    Integer.parseInt(positions[1].strip())));
            cells.add(new Cell(location));
        }
        return cells;
    }

    public void playTick(int tick){
        for (int i = 0; i < tick; i++) {
            for(Cell cell: alive.getSet()){
                playCell(cell,true);
            }
            for(Cell cell: dead.getSet()){
                playCell(cell, false);
            }
//            alive.getSet().forEach(cell -> {
//                playCell(cell, true);});
//            dead.getSet().forEach(cell -> {
//                playCell(cell, false);});
            alive.removeAll(killed);
            alive.addAll(resurrected);
//            dead.addAll(killed);
            dead.clear();
            killed.clear();
            resurrected.clear();
        }
    }

    public Boolean playCell(Cell cell, Boolean aliveFlag){
        Set<Cell> neighbours = cell.getNeighbours();
//        Set<Cell> aliveNeighbours = alive.union(killed).intersection(neighbours);
        Set<Cell> aliveNeighbours = alive.intersection(neighbours);
        Boolean newStateFlag = applyRule(cell, aliveNeighbours.size(), aliveFlag);
        if(aliveFlag){
//            Set<Cell> deadNeighbours = neighbours.diff(aliveNeighbours);
            neighbours.removeAll(aliveNeighbours);
            dead.addAll(neighbours);
        }
        return newStateFlag;
    }

    private Boolean applyRule(Cell cell, Integer size, Boolean aliveFlag) {
        if(aliveFlag){
//            kill from loneliness or over crowding
//            rule 1 and 2
            if (size < 2 || size >3){
                aliveFlag = killCell(cell);
            }
//            rule 3
        }
        else {
//            if a dead cell has exactly 3 neighbours it becomes alive
//            rule 4
            if(size == 3){
                aliveFlag = resurrectCell(cell);
            }
        }
        return aliveFlag;
    }

    private Boolean killCell(Cell cell) {
//        alive.remove(cell);
        killed.add(cell);
        return false;
    }

    private Boolean resurrectCell(Cell cell){
//        dead.remove(cell);
        resurrected.add(cell);
        return true;
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
