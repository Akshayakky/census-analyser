package com.bridgelabz.censusanalyser;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

    private CensusAdapterFactory censusAdapterFactory = new CensusAdapterFactory();

    public enum Country {INDIA, US}

    public enum SortingMode {STATE, STATECODE, POPULATION, DENSITY, AREA}

    Map<String, CensusDAO> censusStateMap = null;

    private Country country;

    public StateCensusAnalyser(Country country, CensusAdapterFactory... censusAdapterFactory) {
        this.country = country;
        if (censusAdapterFactory.length == 1)
            this.censusAdapterFactory = censusAdapterFactory[0];
    }

    public int loadCensusData(String... csvPath) throws CensusAnalyserException {
        CensusAdapter censusAdapter = this.censusAdapterFactory.getCensusData(country);
        censusStateMap = censusAdapter.loadCensusData(csvPath);
        return censusStateMap.size();
    }

    public String getSortedCensusData(SortingMode mode) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> censusCSVComparator = CensusDAO.getSortComparator(mode);
        ArrayList censusDTO = censusStateMap.values().stream().
                sorted(censusCSVComparator).map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(censusDTO);
        return sortedStateCensusJson;
    }
}