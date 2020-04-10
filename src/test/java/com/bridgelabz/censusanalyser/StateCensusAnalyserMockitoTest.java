package com.bridgelabz.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class StateCensusAnalyserMockitoTest {

    @Mock
    CensusAdapter censusAdapter;

    @Mock
    CensusAdapterFactory censusAdapterFactory;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private Map<String, CensusDAO> censusStateMapINDIA;
    private Map<String, CensusDAO> censusStateMapUS;

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "src/test/resources/IndiaCensusData.csv";
    private static final String INDIA_CENSUS_INCORRECT_CSV_FILE_PATH = "src/test/resources/StateCensusInvalidData.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH = "src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_STATECODE_INCORRECT_CSV_FILE_PATH = "src/test/resources/StateCodeInvalidData.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "src/test/resources/USCensusData.csv";

    @Before
    public void setup() {
        censusStateMapINDIA = new HashMap<>();
        censusStateMapUS = new HashMap<>();

        censusStateMapINDIA.put("Maharashtra", new CensusDAO(new IndiaCensusCSV("Maharashtra"
                , 112372972, 307713, 365)));
        censusStateMapINDIA.put("Goa", new CensusDAO(new IndiaCensusCSV("Goa"
                , 1457723, 3702, 394)));
        censusStateMapINDIA.put("Meghalaya", new CensusDAO(new IndiaCensusCSV("Meghalaya"
                , 2964007, 22429, 132)));
        censusStateMapINDIA.put("Tripura", new CensusDAO(new IndiaCensusCSV("Tripura"
                , 3671032, 10486, 350)));
        censusStateMapINDIA.put("Nagaland", new CensusDAO(new IndiaCensusCSV("Nagaland"
                , 1980602, 16579, 119)));

        censusStateMapUS.put("Alabama", new CensusDAO(new USCensusCSV("AL", "Alabama"
                , 4779736, 135767.46, 36.45)));
        censusStateMapUS.put("Florida", new CensusDAO(new USCensusCSV("FL", "Florida"
                , 710231, 1723338.01, 0.46)));
        censusStateMapUS.put("California", new CensusDAO(new USCensusCSV("CA", "California"
                , 6392017, 295233.74, 21.74)));
        censusStateMapUS.put("Hawaii", new CensusDAO(new USCensusCSV("HI", "Hawaii"
                , 2915918, 137731.91, 21.62)));
        censusStateMapUS.put("Washington", new CensusDAO(new USCensusCSV("WA", "Washington"
                , 6724540, 184660.98, 39.07)));

        censusStateMapINDIA.get("Maharashtra").stateCode = "MH";
        censusStateMapINDIA.get("Goa").stateCode = "GA";
        censusStateMapINDIA.get("Meghalaya").stateCode = "ME";
        censusStateMapINDIA.get("Tripura").stateCode = "TR";
        censusStateMapINDIA.get("Nagaland").stateCode = "NL";
    }

    @Test
    public void givenHashMap_WhenDataLoaded_ThenReturnCount() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            int counter = stateCensusAnalyser.loadCensusData();
            Assert.assertEquals(5, counter);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSorted_ThenCheckFirstState() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            int counter = censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Goa", censusCSV[0].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSortedInvalid_ThenCheckSortedList() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            int counter = censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertNotEquals("Goa", censusCSV[1].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenStateNameSorted_ThenCheckLastState() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Tripura", censusCSV[censusCSV.length - 1].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenPopulationSorted_ThenCheckLeastPopulation() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            int counter = censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1457723, censusCSV[0].getPopulation());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenPopulationSorted_ThenCheckHighestPopulation() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(112372972, censusCSV[censusCSV.length - 1].getPopulation());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenPopulationSortedInvalid_ThenCheckHighestPopulation() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertNotEquals(112372971, censusCSV[censusCSV.length - 1].getPopulation());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenDensitySorted_ThenCheckLowestDensity() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(132, censusCSV[1].getDensityPerSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenDensitySorted_ThenCheckHighestDensity() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(394, censusCSV[censusCSV.length - 1].getDensityPerSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenAreaSorted_ThenCheckLowestArea() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(10486, censusCSV[1].getAreaInSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenAreaSorted_ThenCheckHighestArea() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(307713, censusCSV[censusCSV.length - 1].getAreaInSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenFileCorrect_ThenLoadCensusData() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData("src/test/resources/IndiaCensusData.csv", "src/test/resources/IndiaStateCode.csv");
            Assert.assertEquals(29, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenFileCorrect_ThenLoadCensusData() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US);
            int counter = stateCensusAnalyser.loadCensusData("src/test/resources/USCensusData.csv");
            Assert.assertEquals(51, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenCSVFile_WhenFileNameIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData("src/test/resources/IncorrectName.csv", INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCSVFile_WhenFileTypeIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData("src/test/resources/StateCensusData.pdf", INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenCSVFile_WhenDelimiterIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_INCORRECT_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenCSVFile_WhenHeaderIncorrect_ThenThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA);
            int counter = stateCensusAnalyser.loadCensusData(INDIA_CENSUS_INCORRECT_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.type);
        }
    }

    @Test
    public void givenUSCensusData_WhenPopulationSorted_ThenCheckHighestPopulation() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(6724540, censusCSV[censusCSV.length - 1].getPopulation(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenPopulationSorted_ThenCheckLowestPopulation() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(710231, censusCSV[0].getPopulation(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenStateCodeSorted_ThenCheckLowestStateCode() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("AL", censusCSV[0].getStateId());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenStateCodeSorted_ThenCheckHighestStateCode() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("WA", censusCSV[censusCSV.length - 1].getStateId());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenDensitySorted_ThenCheckLowestDensity() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(0.46, censusCSV[0].getPopulationDensity(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenDensitySorted_ThenCheckHighestDensity() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(39.07, censusCSV[censusCSV.length - 1].getPopulationDensity(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenStateSorted_ThenCheckFirstState() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCSV[0].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenStateSorted_ThenCheckLastState() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Washington", censusCSV[censusCSV.length - 1].getState());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenAreaSorted_ThenCheckFirstArea() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(135767.46, censusCSV[0].getTotalArea(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenAreaSorted_ThenCheckLastArea() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(1723338.01, censusCSV[censusCSV.length - 1].getTotalArea(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WhenPopulationSorted_ThenCheckHighestPopulationStateWithDensity() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapINDIA);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.INDIA, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Maharashtra", censusCSV[censusCSV.length - 1].getState());
            Assert.assertEquals(365, censusCSV[censusCSV.length - 1].getDensityPerSqKm(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenPopulationSorted_ThenCheckHighestPopulationStateWithDensity() {
        try {
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMapUS);
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            censusAnalyser.loadCensusData();
            String sortedCensusData = censusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Washington", censusCSV[censusCSV.length - 1].getState());
            Assert.assertEquals(39.07, censusCSV[censusCSV.length - 1].getPopulationDensity(), 0);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
