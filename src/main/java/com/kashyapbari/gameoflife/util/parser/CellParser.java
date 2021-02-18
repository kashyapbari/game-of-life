package com.kashyapbari.gameoflife.util.parser;

import com.kashyapbari.gameoflife.universe.component.Cell;
import com.kashyapbari.gameoflife.util.parser.exception.ParserException;

public class CellParser implements Parser<Cell> {

    @Override
    public Cell parse(String input) throws ParserException {
        try {
            String location[] = input.strip().split(",");
            String locationRow = location[0].strip();
            String locationColumn = location[1].strip();
            int row = Integer.parseInt(locationRow);
            int col = Integer.parseInt(locationColumn);
            return new Cell(row, col);
        }
        catch (IndexOutOfBoundsException e){
            throw new ParserException("Incorrect format must contain 2 comma separated values",input);
        }
        catch (NumberFormatException e) {
            throw new ParserException("Incorrect values- both must be Integers",input);
        }
    }
}
