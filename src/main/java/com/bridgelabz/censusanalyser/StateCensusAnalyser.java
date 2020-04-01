package com.bridgelabz.censusanalyser;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

    Map<String, CensusDAO> censusStateMap = null;
    private Country country;

    public StateCensusAnalyser(Country country) {
        this.country = country;
    }

    public StateCensusAnalyser() {
        this.censusStateMap = new HashMap<String, CensusDAO>();
    }

    public static void main(String[] args) {
        System.out.println("Welcome To Census Analyser Problem");
    }

    public int loadCensusData(String... csvPath) throws CensusAnalyserException {
        CensusAdapter censusAdapter = new CensusAdapterFactory().getCensusData(country, csvPath);
        censusStateMap = censusAdapter.loadCensusData(csvPath);
        return censusStateMap.size();
    }

    public String getSortedCensusData(SortingMode mode) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> censusCSVComparator = CensusDAO.getSortComparator(mode);
        ArrayList censusDTO = censusStateMap.values().stream().
                sorted(censusCSVComparator).map(censusDAO -> censusDAO.getCensusDTO())
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(censusDTO);
        return sortedStateCensusJson;
    }

    public enum Country {INDIA, US}

    public enum SortingMode {STATE, STATECODE, POPULATION, DENSITY, AREA}
}
