package com.bridgelabz.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTestCases {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "src/test/resources/IndiaCensusData.csv";
    private static final String INDIA_CENSUS_INCORRECT_CSV_FILE_PATH = "src/test/resources/StateCensusInvalidData.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH = "src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_STATECODE_INCORRECT_CSV_FILE_PATH = "src/test/resources/StateCodeInvalidData.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "src/test/resources/USCensusData.csv";

    //Test Case TC1.1
    @Test
    public void givenCSVFile_WhenFileCorrect_ThenReturnTrue() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(29, counter);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //Test Case TC1.2
    @Test
    public void givenCSVFile_WhenFileNameIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData("src/test/resources/IncorrectName.csv", INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    //Test Case TC1.3
    @Test
    public void givenCSVFile_WhenFileTypeIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData("src/test/resources/StateCensusData.pdf", INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
        }
    }

    //Test Case TC1.4
    @Test
    public void givenCSVFile_WhenDelimiterIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_INCORRECT_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC1.5
    @Test
    public void givenCSVFile_WhenHeaderIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_INCORRECT_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC2.1
    @Test
    public void givenStateCodeCSVFile_WhenFileCorrect_ThenReturnCSVCount() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(29, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    //Test Case TC2.2
    @Test
    public void givenStateCodeCSVFile_WhenFileNameIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData("src/test/resources/IncorrectName.csv", INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    //Test Case TC2.3
    @Test
    public void givenStateCodeCSVFile_WhenFileTypeIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData("src/test/resources/IndiaStateCode.pdf", INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
        }
    }

    //Test Case TC2.4
    @Test
    public void givenStateCodeCSVFile_WhenDelimiterIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_INCORRECT_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    //Test Case TC2.5
    @Test
    public void givenStateCodeCSVFile_WhenHeaderIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_INCORRECT_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenCSVFile_WhenFileCorrect_ThenReturnCorrectCount() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertNotEquals(299, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenFileCorrect_ThenReturnCorrectCount() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertNotEquals(487, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSorted_ThenCheckFirstState() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSorted_ThenCheckSortedList() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertNotEquals("Andhra Pradesh", censusCSV[1].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSorted_ThenCheckLastState() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal", censusCSV[censusCSV.length - 1].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCensusDAOList_WhenNull_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenPopulationSorted_ThenCheckLeastPopulation() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(607688, censusCSV[0].getPopulation());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenPopulationSortedInvalid_ThenCheckLeastPopulation() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertNotEquals(607687, censusCSV[0].getPopulation());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenPopulationSorted_ThenCheckHighestPopulation() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, censusCSV[censusCSV.length - 1].getPopulation());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenPopulationSortedInvalid_ThenCheckHighestPopulation() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertNotEquals(199812342, censusCSV[censusCSV.length - 1].getPopulation());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenDensitySorted_ThenCheckLowestDensity() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(52, censusCSV[1].getDensityPerSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenDensitySorted_ThenCheckHighestDensity() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, censusCSV[censusCSV.length - 1].getDensityPerSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenAreaSorted_ThenCheckLowestArea() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(3702, censusCSV[1].getAreaInSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenAreaSorted_ThenCheckHighestArea() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239, censusCSV[censusCSV.length - 1].getAreaInSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenFileCorrect_ThenReturnCount() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US);
            int counter = stateCensusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenPopulationSorted_ThenCheckHighestPopulation() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(37253956, censusCSV[censusCSV.length - 1].getPopulation(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenPopulationSorted_ThenCheckLowestPopulation() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(563626, censusCSV[0].getPopulation(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenStateCodeSorted_ThenCheckLowestStateCode() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("AK", censusCSV[0].getStateId());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenStateCodeSorted_ThenCheckHighestStateCode() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("WY", censusCSV[censusCSV.length-1].getStateId());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
