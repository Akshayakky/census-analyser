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
            int counter = stateCensusAnalyser.loadIndiaCensusData(SAMPLE_CSV_FILE_PATH);
            Assert.assertEquals(29, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    //Test Case TC1.2
    @Test
    public void givenCSVFile_WhenFileNameIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaCensusData("src/test/resources/IncorrectName.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    //Test Case TC1.3
    @Test
    public void givenCSVFile_WhenFileTypeIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaCensusData("src/test/resources/StateCensusData.pdf");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
        }
    }

    //Test Case TC1.4
    @Test
    public void givenCSVFile_WhenDelimiterIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaCensusData(SAMPLE_CSV_INCORRECT_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC1.5
    @Test
    public void givenCSVFile_WhenHeaderIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaCensusData(SAMPLE_CSV_INCORRECT_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC2.1
    @Test
    public void givenStateCodeCSVFile_WhenFileCorrect_ThenReturnCSVCount() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaStateCode(STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(37, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    //Test Case TC2.2
    @Test
    public void givenStateCodeCSVFile_WhenFileNameIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaStateCode("src/test/resources/IncorrectName.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    //Test Case TC2.3
    @Test
    public void givenStateCodeCSVFile_WhenFileTypeIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaStateCode("src/test/resources/StateCode.pdf");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
        }
    }

    //Test Case TC2.4
    @Test
    public void givenStateCodeCSVFile_WhenDelimiterIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaStateCode(STATECODE_CSV_INCORRECT_DATA_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC2.5
    @Test
    public void givenStateCodeCSVFile_WhenHeaderIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaStateCode(STATECODE_CSV_INCORRECT_DATA_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenCSVFile_WhenFileCorrect_ThenReturnCorrectCount() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaCensusData(SAMPLE_CSV_FILE_PATH);
            Assert.assertNotEquals(299, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenFileCorrect_ThenReturnCorrectCount() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.loadIndiaStateCode(STATECODE_CSV_FILE_PATH);
            Assert.assertNotEquals(487, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSorted_ThenCheckFirstState() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SAMPLE_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSorted_ThenCheckSortedList() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SAMPLE_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertNotEquals("Andhra Pradesh", censusCSV[1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSorted_ThenCheckLastState() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SAMPLE_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCensusDAOList_WhenNull_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateCodeSorted_ThenCheckFirstStateCode() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SAMPLE_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("AP", censusCSV[0].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateCodeSorted_ThenCheckLastStateCode() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SAMPLE_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("WB", censusCSV[censusCSV.length - 1].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateCodeSortedInvalid_ThenCheckFirstStateCode() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndiaCensusData(SAMPLE_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertNotEquals("WB", censusCSV[1].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
