package com.kashyapbari.gameoflife.util.parser;

import com.kashyapbari.gameoflife.universe.component.Cell;
import com.kashyapbari.gameoflife.util.parser.exception.ParserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellParserTest {


    @Test
    @DisplayName("Empty Cell String throws Parser Exception")
    public void emptyCellValueThrowsParserException(){
        String input = "";

        Exception exception = assertThrows(ParserException.class, ()->new CellParser().parse(input));
        assert (exception.getMessage().contains("Incorrect format must contain 2 comma separated values"));
    }

    @Test
    @DisplayName("Comma Seperated Cell String Creates Correct Cell")
    public void cellCreatedByCommaSeperatedIntegerStrings() throws ParserException {
        String input = "1,2";

        Cell cell = new CellParser().parse(input);
        assert (cell.equals(new Cell(1,2)));
    }

    @Test
    @DisplayName("No Comma Cell String throws Parser Exception")
    public void noCommaCellValueThrowsParserException(){
        String input = "1234";

        Exception exception = assertThrows(ParserException.class, ()->new CellParser().parse(input));
        assert (exception.getMessage().contains("Incorrect format must contain 2 comma separated values"));
    }

    @Test
    @DisplayName("Non Integer Cell String throws Parser Exception")
    public void noIntegerCellValueThrowsParserException(){
        String input = "1234,:)";

        Exception exception = assertThrows(ParserException.class, ()->new CellParser().parse(input));
        assert (exception.getMessage().contains("Incorrect values- both must be Integers"));
    }

    @Test
    @DisplayName("Negative Cell String Creates Correct Cell")
    public void cellCreatedByNegativeIntegerStrings() throws ParserException {
        String input = "-11,2";

        Cell cell = new CellParser().parse(input);
        assert (cell.equals(new Cell(-11,2)));
    }

    @Test
    @DisplayName("Large Cell String Creates Correct Cell")
    public void cellCreatedByLargeIntegerStrings() throws ParserException {
        String input = "4456711,99999123";

        Cell cell = new CellParser().parse(input);
        assert (cell.equals(new Cell(4456711,99999123)));
    }

    @Test
    @DisplayName("Multiple Comma Seperated Cell String Creates Cell with only initial values")
    public void multipleCommaSeperatedIntegerStringsCreatesCellWithIntialValues() throws ParserException {
        String input = "1,2,4,5,6";

        Cell cell = new CellParser().parse(input);
        assert (cell.equals(new Cell(1,2)));
    }


}