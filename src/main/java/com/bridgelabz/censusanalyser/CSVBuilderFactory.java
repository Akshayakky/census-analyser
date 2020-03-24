package com.bridgelabz.censusanalyser;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilderImpl();
    }
}
