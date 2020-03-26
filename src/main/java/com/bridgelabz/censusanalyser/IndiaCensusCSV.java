package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public long population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public double densityPerSqKm;
}
