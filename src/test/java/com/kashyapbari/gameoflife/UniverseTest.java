package com.kashyapbari.gameoflife;

import com.kashyapbari.gameoflife.model.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {

    public static String[] cellSparseMatrix(){
        return new String[] {
                "1, 1 \n 1, 2 \n 2, 1 \n 2, 2",
                "00, 1 \n1, 0 \n   2, 1 \n0,    2\n 1,2"
        };
    }

    @ParameterizedTest
    @MethodSource(value = "cellSparseMatrix")
    public void testCellSetFromString(String cellMatrix){
        Universe universe = new Universe(cellMatrix);
        assertNotNull(universe);
    }

    public static String[] cellPlay(){
        return new String[] {
//                cell to play, active cells in universe, playing cell current state, playing cell new state
                "1,1:1, 1 \n 1, 2 \n 2, 1 \n 2, 2:true:true",
                "1,0:1,1 \n1,0 \n 1,2:true:false",
                "1,1:1,1 \n1,0 \n 1,2:true:true",
                "1,4:1, 1\n1, 2\n1, 3\n2, 2\n2, 3\n2, 4:false:true",
                "3,3:1, 1\n1, 2\n1, 3\n2, 2\n2, 3\n2, 4:false:true"
        };
    }

    @ParameterizedTest
    @MethodSource(value = "cellPlay")
    public void testplayCell(String cellPlays){
        String[] cellPlay = cellPlays.split(":");
        Universe universe = new Universe(cellPlay[1].strip());
        Cell cell = new Cell(cellPlay[0].strip());
        Boolean aliveFlag = Boolean.parseBoolean(cellPlay[2].strip());
        Boolean result = Boolean.parseBoolean(cellPlay[3].strip());
        assertEquals(result, universe.playCell(cell,aliveFlag));
    }


    public static String[] tickPlayState(){
        return new String[] {
            "0, 1\n1, 0\n2, 1\n0, 2\n1, 2 : 0, 1\n1, 0\n2, 1\n0, 2\n1, 2 ",
            "1, 1\n1, 0\n1, 2 : 1, 1\n0, 1\n2, 1",
            "1, 1\n1, 2\n1, 3\n2, 2\n2, 3\n2, 4 : 0, 2\n1, 1\n1, 4\n2, 1\n2, 4\n3, 3"
        };
    }

    @ParameterizedTest
    @MethodSource(value = "tickPlayState")
    void testplayTick(String tickPlays) {
        String[] tickPlay = tickPlays.split(":");
        Universe universe = new Universe(tickPlay[0]);
        universe.playTick(1);
        Universe nextUniverse = new Universe(tickPlay[1]);
        assertEquals(universe, nextUniverse);
    }
}