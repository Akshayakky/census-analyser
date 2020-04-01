package com.bridgelabz.censusanalyser;

import java.util.Comparator;

public class CensusDAO {
    public String state;
    public String stateCode;
    public long population;
    public double totalArea;
    public double populationDensity;


    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.getState();
        population = indiaCensusCSV.getPopulation();
        totalArea = indiaCensusCSV.getAreaInSqKm();
        populationDensity = indiaCensusCSV.getDensityPerSqKm();
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.getState();
        stateCode = usCensusCSV.getStateId();
        population = usCensusCSV.getPopulation();
        totalArea = usCensusCSV.getTotalArea();
        populationDensity = usCensusCSV.getPopulationDensity();
    }

    public static Comparator<CensusDAO> getSortComparator(StateCensusAnalyser.SortingMode mode) {
        switch (mode) {
            case STATE:
                return Comparator.comparing(census -> census.state);
            case POPULATION:
                return Comparator.comparing(census -> census.population);
            case DENSITY:
                return Comparator.comparing(census -> census.populationDensity);
            case AREA:
                return Comparator.comparing(census -> census.totalArea);
            case STATECODE:
                return Comparator.comparing(census -> census.stateCode);
            default:
                return null;
        }
    }

    public Object getCensusDTO(StateCensusAnalyser.Country country) {
        if(country.equals(StateCensusAnalyser.Country.INDIA))
            return new IndiaCensusCSV(state, population, totalArea, populationDensity);
        return new USCensusCSV(stateCode, state, population, totalArea, populationDensity);
    }
}
