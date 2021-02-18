package com.kashyapbari.gameoflife.util.parser;

import com.kashyapbari.gameoflife.util.parser.exception.ParserException;

public interface Parser<T> {
    T parse(String input) throws ParserException;
}
