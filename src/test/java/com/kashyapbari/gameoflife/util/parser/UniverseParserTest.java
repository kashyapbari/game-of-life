package com.kashyapbari.gameoflife.util.parser;

import com.kashyapbari.gameoflife.Universe;
import com.kashyapbari.gameoflife.util.parser.exception.ParserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UniverseParserTest {

    @Test
    @DisplayName("Empty String returns empty Universe")
    public void emptyStringReturnsEmptyUniverse() throws ParserException {
        String input = "";

        Universe universe = new UniverseParser().parse(input);

        assertEquals(universe, new Universe(new HashSet<>()));
    }

    @Test
    @DisplayName("Single cell String returns Non Empty Universe")
    public void singleCellReturnsNonEmptyUniverse() throws ParserException {
        String input = "1,2\n";

        Universe universe = new UniverseParser().parse(input);

        assert(universe.getAlive().size() != 0);
    }

    @Test
    @DisplayName("Three Cell String returns Universe with Three cells")
    public void threeCellStringReturnUniverseWithThreeCells()  throws ParserException{
        String input = "1,2\n2,3\n3,4";

        Universe universe = new UniverseParser().parse(input);

        assert(universe.getAlive().size() == 3);
    }

    @Test
    @DisplayName("Duplicate Cell String returns Universe with Unique cells")
    public void duplicateCellStringReturnUniverseWithUniqueCells()  throws ParserException{
        String input = "2,3\n2,3";

        Universe universe = new UniverseParser().parse(input);

        assert(universe.getAlive().size() == 1);
    }

    @Test
    @DisplayName("Non Integer Cell Characters throws Parser Exception")
    public void nonIntegerCellCharsThrowsParserException(){
        String input = "2,3\n$the$,3";

        Exception exception = assertThrows(ParserException.class, ()->new UniverseParser().parse(input));
        assert(exception.getMessage().contains("Incorrect values- both must be Integers"));
    }

    @Test
    @DisplayName("Missing Comma in the Cell Values throws Parser Exception")
    public void missingCommaCellStringThrowsParserException(){
        String input = "2,3\n33";

        Exception exception = assertThrows(ParserException.class, ()->new UniverseParser().parse(input));
        assert(exception.getMessage().contains("Incorrect format must contain 2 comma separated values"));
    }





    
}