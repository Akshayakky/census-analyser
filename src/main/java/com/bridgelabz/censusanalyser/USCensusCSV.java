package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

    @CsvBindByName(column = "State Id", required = true)
    private String stateId;
    @CsvBindByName(column = "State", required = true)
    private String state;
    @CsvBindByName(column = "Population", required = true)
    private long population;
    @CsvBindByName(column = "Total area", required = true)
    private double totalArea;
    @CsvBindByName(column = "Population Density", required = true)
    private double populationDensity;

    public USCensusCSV(String stateId, String state, long population, double totalArea, double populationDensity) {
        this.stateId = stateId;
        this.state = state;
        this.population = population;
        this.totalArea = totalArea;
        this.populationDensity = populationDensity;
    }

    public USCensusCSV() {

    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(double populationDensity) {
        this.populationDensity = populationDensity;
    }
}
