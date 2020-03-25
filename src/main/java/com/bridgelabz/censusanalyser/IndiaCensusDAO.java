package com.bridgelabz.censusanalyser;

import java.util.Comparator;

public class IndiaCensusDAO {
    public String state;
    public String stateCode;
    public long population;
    public long areaInSqKm;
    public int densityPerSqKm;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
    }

    public static Comparator<IndiaCensusDAO> getSortComparator(StateCensusAnalyser.SortingMode mode) {
        switch (mode) {
            case STATE:
                return Comparator.comparing(census -> census.state);
            case POPULATION:
                return Comparator.comparing(census -> census.population);
            case DENSITY:
                return Comparator.comparing(census -> census.densityPerSqKm);
            case AREA:
                return Comparator.comparing(census -> census.areaInSqKm);
            case STATECODE:
                return Comparator.comparing(census -> census.stateCode);
            default:
                return null;
        }
    }
}
