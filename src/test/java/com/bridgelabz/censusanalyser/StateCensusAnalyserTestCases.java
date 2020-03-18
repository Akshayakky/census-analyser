package com.bridgelabz.censusanalyser;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTestCases {
    private static final String SAMPLE_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String SAMPLE_CSV_INCORRECT_FILE_PATH = "src/test/resources/StateCensusInvalidData.csv";
    private static final String SAMPLE_CSV_INCORRECT_FILE_NAME = "src/test/resources/IncorrectName.csv";
    private static final String SAMPLE_CSV_INCORRECT_FILE_TYPE = "src/test/resources/StateCensusData.pdf";

    @Test
    public void givenCSVFile_WhenFileCorrect_ThenReturnTrue() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int counter = stateCensusAnalyser.getNumberOfRecords(SAMPLE_CSV_FILE_PATH);
            Assert.assertEquals(29, counter);
        } catch (CensusAnalyserException e) {
        }
    }
}