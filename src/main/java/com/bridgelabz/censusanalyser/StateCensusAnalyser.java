package com.bridgelabz.censusanalyser;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

    public enum Country {INDIA, US}

    public enum SortingMode {STATE, STATECODE, POPULATION, DENSITY, AREA}

    Map<String, CensusDAO> censusStateMap = null;

    public StateCensusAnalyser() {
        this.censusStateMap = new HashMap<String, CensusDAO>();
    }

    public static void main(String[] args) {
        System.out.println("Welcome To Census Analyser Problem");
    }

    public int loadCensusData(StateCensusAnalyser.Country country, String... csvPath) throws CensusAnalyserException {
        censusStateMap = new CensusAdapterFactory().getCensusData(country, csvPath);
        return censusStateMap.size();
    }

    public String getSortedCensusData(SortingMode mode) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> censusCSVComparator = CensusDAO.getSortComparator(mode);
        List<CensusDAO> censusDAOS = censusStateMap.values().stream().collect(Collectors.toList());
        this.sort(censusCSVComparator, censusDAOS);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    public void sort(Comparator<CensusDAO> censusCSVComparator, List<CensusDAO> censusDAOS) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                CensusDAO census1 = censusDAOS.get(j);
                CensusDAO census2 = censusDAOS.get(j + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    censusDAOS.set(j, census2);
                    censusDAOS.set(j + 1, census1);
                }
            }
        }
    }
}
