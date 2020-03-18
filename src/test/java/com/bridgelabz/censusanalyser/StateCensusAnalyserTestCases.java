package com.bridgelabz.censusanalyser;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTestCases {
    private static final String SAMPLE_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String SAMPLE_CSV_INCORRECT_FILE_PATH = "src/test/resources/StateCensusInvalidData.csv";
    private static final String SAMPLE_CSV_INCORRECT_FILE_NAME = "src/test/resources/IncorrectName.csv";
    private static final String SAMPLE_CSV_INCORRECT_FILE_TYPE = "src/test/resources/StateCensusData.pdf";

    //Test Case TC1.1
    @Test
    public void givenCSVFile_WhenFileCorrect_ThenReturnTrue() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.getNumberOfRecords(SAMPLE_CSV_FILE_PATH);
            Assert.assertEquals(29, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    //Test Case TC1.2
    @Test
    public void givenCSVFile_WhenFileNameIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.getNumberOfRecords(SAMPLE_CSV_INCORRECT_FILE_NAME);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    //Test Case TC1.3
    @Test
    public void givenCSVFile_WhenFileTypeIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.getNumberOfRecords(SAMPLE_CSV_INCORRECT_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
        }
    }

    //Test Case TC1.4
    @Test
    public void givenCSVFile_WhenDelimiterIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.getNumberOfRecords(SAMPLE_CSV_INCORRECT_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }
}