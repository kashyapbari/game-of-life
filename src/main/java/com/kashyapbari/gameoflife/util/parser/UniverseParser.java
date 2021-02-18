package com.kashyapbari.gameoflife.util.parser;

import com.kashyapbari.gameoflife.Universe;
import com.kashyapbari.gameoflife.container.Set;
import com.kashyapbari.gameoflife.universe.component.Cell;
import com.kashyapbari.gameoflife.util.parser.exception.ParserException;

import java.util.HashSet;

public class UniverseParser implements Parser<Universe> {

    @Override
    public Universe parse(String input) throws ParserException {
        Parser<Cell> cellParser = new CellParser();
        Set<Cell> set = new Set<>(new HashSet<>());
        for(String cellLine: input.split("\n+")){
                if (!cellLine.isBlank()){
                    set.add(cellParser.parse(cellLine));
                }
        }
        return new Universe(set);
    }
}
