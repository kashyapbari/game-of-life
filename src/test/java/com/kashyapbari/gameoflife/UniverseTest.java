package com.kashyapbari.gameoflife;

import com.kashyapbari.gameoflife.model.Cell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniverseTest {

    @Test
    @DisplayName("One cell In an Empty Universe Dies")
    public void oneCellInAnEmptyUniverseDies() {
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(new Cell(0, 0));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assertEquals(universe, new Universe(new HashSet<>()));
    }

    @Test
    @DisplayName(("Two Neighbouring cells Die"))
    public void twoNeighbouringCellsDie() {
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(new Cell(0, 0));
        aliveSet.add(new Cell(0, 1));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assertEquals(universe, new Universe(new HashSet<>()));
    }

    @Test
    @DisplayName("Three Far away cells Die")
    public void threeSeperateCellsDie() {
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(new Cell(0, 0));
        aliveSet.add(new Cell(2, 0));
        aliveSet.add(new Cell(2, 2));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assertEquals(universe, new Universe(new HashSet<>()));
    }

    @Test
    @DisplayName("Cell with Two neighbours Lives")
    public void cellWithTwoNeighboursLives() {
        Cell cell = new Cell(1, 1);
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(cell);
        aliveSet.add(new Cell(0, 0));
        aliveSet.add(new Cell(2, 2));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assert (universe.getAlive().contains(cell));
    }

    @Test
    @DisplayName("Cell with Three neighbours Lives")
    public void cellWithThreeNeighboursLives() {
        Cell cell = new Cell(1, 1);
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(cell);
        aliveSet.add(new Cell(0, 0));
        aliveSet.add(new Cell(2, 2));
        aliveSet.add(new Cell(0, 2));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assert (universe.getAlive().contains(cell));

    }


    @Test
    @DisplayName("Cell with more than Three neighbours Dies")
    public void cellWithMoreThanThreeNeighboursDies() {
        Cell cell = new Cell(1, 1);
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(cell);
        aliveSet.add(new Cell(0, 0));
        aliveSet.add(new Cell(2, 2));
        aliveSet.add(new Cell(0, 2));
        aliveSet.add(new Cell(2, 0));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assert (!universe.getAlive().contains(cell));
    }

    @Test
    @DisplayName("Cell with Exactly Three Neighbours Resurrects")
    public void cellWithExactlyThreeNeighboursResurrects() {
        Cell cell = new Cell(1, 1);
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(new Cell(0, 0));
        aliveSet.add(new Cell(2, 2));
        aliveSet.add(new Cell(0, 2));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assert (universe.getAlive().contains(cell));
    }

    @Test
    @DisplayName("Block Pattern - Still Life")
    public void blockPattern() {
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(new Cell(1, 1));
        aliveSet.add(new Cell(1, 2));
        aliveSet.add(new Cell(2, 1));
        aliveSet.add(new Cell(2, 2));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assertEquals(universe, new Universe(new HashSet<>(aliveSet)));
    }

    @Test
    @DisplayName("Boat Pattern - Still Life")
    public void BoatPattern() {
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(new Cell(0, 1));
        aliveSet.add(new Cell(1, 0));
        aliveSet.add(new Cell(2, 1));
        aliveSet.add(new Cell(0, 2));
        aliveSet.add(new Cell(1, 2));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        assertEquals(universe, new Universe(new HashSet<>(aliveSet)));
    }

    @Test
    @DisplayName("Blinker Pattern - Oscillator")
    public void BlinkerPattern() {
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(new Cell(1, 1));
        aliveSet.add(new Cell(1, 0));
        aliveSet.add(new Cell(1, 2));
        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        HashSet<Cell> newAliveSet = new HashSet<>();
        newAliveSet.add(new Cell(1, 1));
        newAliveSet.add(new Cell(0, 1));
        newAliveSet.add(new Cell(2, 1));

        assertEquals(universe, new Universe(newAliveSet));
    }

    @Test
    @DisplayName("Toad pattern - two phase oscillator")
    public void ToadPattern() {
        HashSet<Cell> aliveSet = new HashSet<>();
        aliveSet.add(new Cell(1, 1));
        aliveSet.add(new Cell(1, 2));
        aliveSet.add(new Cell(1, 3));
        aliveSet.add(new Cell(2, 2));
        aliveSet.add(new Cell(2, 3));
        aliveSet.add(new Cell(2, 4));

        Universe universe = new Universe(aliveSet);

        universe.playTick(1);

        HashSet<Cell> newAliveSet = new HashSet<>();
        newAliveSet.add(new Cell(0, 2));
        newAliveSet.add(new Cell(1, 1));
        newAliveSet.add(new Cell(1, 4));
        newAliveSet.add(new Cell(2, 1));
        newAliveSet.add(new Cell(2, 4));
        newAliveSet.add(new Cell(3, 3));

        assertEquals(universe, new Universe(newAliveSet));
    }


}
