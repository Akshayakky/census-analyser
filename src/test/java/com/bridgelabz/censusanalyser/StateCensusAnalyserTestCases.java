package com.bridgelabz.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTestCases {
    private static final String SAMPLE_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String SAMPLE_CSV_INCORRECT_FILE_PATH = "src/test/resources/StateCensusInvalidData.csv";
    private static String STATECODE_CSV_FILE_PATH = "src/test/resources/StateCode.csv";
    private static String STATECODE_CSV_INCORRECT_DATA_FILE_PATH = "src/test/resources/StateCodeInvalidData.csv";

    //Test Case TC1.1
    @Test
    public void givenCSVFile_WhenFileCorrect_ThenReturnTrue() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV(SAMPLE_CSV_FILE_PATH, IndiaCensusCSV.class);
            Assert.assertEquals(29, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    //Test Case TC1.2
    @Test
    public void givenCSVFile_WhenFileNameIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV("src/test/resources/IncorrectName.csv", IndiaCensusCSV.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    //Test Case TC1.3
    @Test
    public void givenCSVFile_WhenFileTypeIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV("src/test/resources/StateCensusData.pdf", IndiaCensusCSV.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
        }
    }

    //Test Case TC1.4
    @Test
    public void givenCSVFile_WhenDelimiterIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV(SAMPLE_CSV_INCORRECT_FILE_PATH, IndiaCensusCSV.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC1.5
    @Test
    public void givenCSVFile_WhenHeaderIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV(SAMPLE_CSV_INCORRECT_FILE_PATH, IndiaCensusCSV.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC2.1
    @Test
    public void givenStateCodeCSVFile_WhenFileCorrect_ThenReturnTrue() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV(STATECODE_CSV_FILE_PATH, CSVStates.class);
            Assert.assertEquals(37, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    //Test Case TC2.2
    @Test
    public void givenStateCodeCSVFile_WhenFileNameIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV("src/test/resources/IncorrectName.csv", CSVStates.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    //Test Case TC2.3
    @Test
    public void givenStateCodeCSVFile_WhenFileTypeIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV("src/test/resources/StateCode.pdf", CSVStates.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
        }
    }

    //Test Case TC2.4
    @Test
    public void givenStateCodeCSVFile_WhenDelimiterIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV(STATECODE_CSV_INCORRECT_DATA_FILE_PATH, CSVStates.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC2.5
    @Test
    public void givenStateCodeCSVFile_WhenHeaderIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadCSV(STATECODE_CSV_INCORRECT_DATA_FILE_PATH, CSVStates.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenListSorted_ThenCheckFirstState(){
        StateCensusAnalyser censusAnalyser =  new StateCensusAnalyser();
        try {
            censusAnalyser.loadCSV(SAMPLE_CSV_FILE_PATH, IndiaCensusCSV.class);
        String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenListSorted_ThenCheckLastState(){
        StateCensusAnalyser censusAnalyser =  new StateCensusAnalyser();
        try {
            censusAnalyser.loadCSV(SAMPLE_CSV_FILE_PATH, IndiaCensusCSV.class);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal", censusCSV[censusCSV.length-1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}