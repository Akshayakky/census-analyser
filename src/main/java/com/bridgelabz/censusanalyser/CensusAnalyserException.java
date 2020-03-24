package com.bridgelabz.censusanalyser;

public class CensusAnalyserException extends Throwable {

    enum ExceptionType {
        NO_SUCH_FILE, INCORRECT_FILE_TYPE, DELIMITER_OR_HEADER_INCORRECT, CENSUS_FILE_PROBLEM, NO_CENSUS_DATA
    }

    ExceptionType type;

    CensusAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
