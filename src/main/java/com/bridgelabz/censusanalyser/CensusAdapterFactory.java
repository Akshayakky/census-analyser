package com.bridgelabz.censusanalyser;

public class CensusAdapterFactory {
    public <E> CensusAdapter getCensusData(StateCensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(StateCensusAnalyser.Country.INDIA))
            return new IndiaCensusAdapter();
        else if (country.equals(StateCensusAnalyser.Country.US))
            return new USCensusAdapter();
        else
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INVALID_COUNTRY, "Incorrect Country");
    }
}
