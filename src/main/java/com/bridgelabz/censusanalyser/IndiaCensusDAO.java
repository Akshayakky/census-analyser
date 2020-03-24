package com.bridgelabz.censusanalyser;

public class IndiaCensusDAO {
    public String state;
    public long population;
    public long areaInSqKm;
    public int densityPerSqKm;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
    }
}
