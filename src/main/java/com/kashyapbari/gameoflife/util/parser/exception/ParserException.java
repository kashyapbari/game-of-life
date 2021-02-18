package com.kashyapbari.gameoflife.util.parser.exception;

public class ParserException extends Exception{
    public ParserException(String message, String input) {
        super(message+" input:"+input);
    }
}
