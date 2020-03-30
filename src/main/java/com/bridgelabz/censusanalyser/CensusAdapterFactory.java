package com.bridgelabz.censusanalyser;

import java.util.Map;

public class CensusAdapterFactory {
    public <E> Map<String, CensusDAO> getCensusData(StateCensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(StateCensusAnalyser.Country.INDIA))
            return new IndiaCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(StateCensusAnalyser.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INVALID_COUNTRY, "Incorrect Country");
    }
}
