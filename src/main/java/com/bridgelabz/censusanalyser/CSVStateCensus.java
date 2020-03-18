package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    private String state;
    @CsvBindByName(column = "Population", required = true)
    private long population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    private long areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private int densityPerSqKm;

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

    public long getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(long areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public int getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(int densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }
}
