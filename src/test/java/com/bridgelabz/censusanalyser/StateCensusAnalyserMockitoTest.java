package com.bridgelabz.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
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

    public static Map<String, CensusDAO> getIndiaCensusStateMap() {
        Map<String, CensusDAO> censusStateMap = new HashMap<>();

        censusStateMap.put("Maharashtra", new CensusDAO(new IndiaCensusCSV("Maharashtra"
                , 112372972, 307713, 365)));
        censusStateMap.put("Goa", new CensusDAO(new IndiaCensusCSV("Goa"
                , 1457723, 3702, 394)));
        censusStateMap.put("Meghalaya", new CensusDAO(new IndiaCensusCSV("Meghalaya"
                , 2964007, 22429, 132)));
        censusStateMap.put("Tripura", new CensusDAO(new IndiaCensusCSV("Tripura"
                , 3671032, 10486, 350)));
        censusStateMap.put("Nagaland", new CensusDAO(new IndiaCensusCSV("Nagaland"
                , 1980602, 16579, 119)));

        censusStateMap.get("Maharashtra").stateCode = "MH";
        censusStateMap.get("Goa").stateCode = "GA";
        censusStateMap.get("Meghalaya").stateCode = "ME";
        censusStateMap.get("Tripura").stateCode = "TR";
        censusStateMap.get("Nagaland").stateCode = "NL";

        return censusStateMap;
    }

    public static Map<String, CensusDAO> getUSCensusStateMap() {
        Map<String, CensusDAO> censusStateMap = new HashMap<>();

        censusStateMap.put("Alabama", new CensusDAO(new USCensusCSV("AL", "Alabama"
                , 4779736, 135767.46, 36.45)));
        censusStateMap.put("Florida", new CensusDAO(new USCensusCSV("FL", "Florida"
                , 710231, 1723338.01, 0.46)));
        censusStateMap.put("California", new CensusDAO(new USCensusCSV("CA", "California"
                , 6392017, 295233.74, 21.74)));
        censusStateMap.put("Hawaii", new CensusDAO(new USCensusCSV("HI", "Hawaii"
                , 2915918, 137731.91, 21.62)));
        censusStateMap.put("Nagaland", new CensusDAO(new USCensusCSV("WA", "Washington"
                , 6724540, 184660.98, 39.07)));

        return censusStateMap;
    }

    @Test
    public void givenHashMap_WhenDataLoaded_ThenReturnCount() {
        try {
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
    public void givenUSCensusData_WhenFileCorrect_ThenReturnCount() {
        try {
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.Country.US, censusAdapterFactory);
            int counter = stateCensusAnalyser.loadCensusData();
            Assert.assertEquals(5, counter);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenPopulationSorted_ThenCheckHighestPopulation() {
        try {
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getIndiaCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.INDIA)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
            Map<String, CensusDAO> censusStateMap = getUSCensusStateMap();
            when(censusAdapterFactory.getCensusData(StateCensusAnalyser.Country.US)).thenReturn(censusAdapter);
            when(censusAdapter.loadCensusData()).thenReturn(censusStateMap);
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
