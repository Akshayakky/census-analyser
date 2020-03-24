package com.bridgelabz.censusanalyser;

public class CSVBuilderException extends Throwable {

    enum ExceptionType {
        DELIMITER_OR_HEADER_INCORRECT
    }

    ExceptionType type;

    CSVBuilderException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
