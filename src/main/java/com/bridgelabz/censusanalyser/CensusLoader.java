package com.bridgelabz.censusanalyser;

import com.bridgelabz.csvbuilder.CSVBuilderException;
import com.bridgelabz.csvbuilder.CSVBuilderFactory;
import com.bridgelabz.csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusLoader {

    public <E> Map<String, CensusDAO> loadCensusData(StateCensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(StateCensusAnalyser.Country.INDIA))
            return this.loadCensusData(IndiaCensusCSV.class, csvFilePath);
        else if (country.equals(StateCensusAnalyser.Country.US))
            return this.loadCensusData(USCensusCSV.class, csvFilePath);
        else
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INVALID_COUNTRY, "Incorrect Country");
    }

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> censusStateMap = new HashMap<>();
        int lastIndexOf = csvFilePath[0].lastIndexOf(".");
        String fileExtension = (lastIndexOf == -1) ? "" : csvFilePath[0].substring(lastIndexOf);
        if (!fileExtension.equals(".csv"))
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, "File Type Incorrect");
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]));
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> csvIterable = () -> csvIterator;
            if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyser.IndiaCensusCSV"))
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusStateMap.put(censusCSV.getState(), new CensusDAO(censusCSV)));
            if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyser.USCensusCSV"))
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusStateMap.put(censusCSV.getState(), new CensusDAO(censusCSV)));
            if (csvFilePath.length == 1)
                return censusStateMap;
            return this.loadIndiaStateCode(censusStateMap, csvFilePath[1]);
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, "No Such File Exists");
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.getMessage());
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect. Error While Building CSV.");
        }
    }

    public Map<String, CensusDAO> loadIndiaStateCode(Map<String, CensusDAO> censusStateMap, String IndianStateCodeFilePath) throws CensusAnalyserException {
        int lastIndexOf = IndianStateCodeFilePath.lastIndexOf(".");
        String fileExtension = (lastIndexOf == -1) ? "" : IndianStateCodeFilePath.substring(lastIndexOf);
        if (!fileExtension.equals(".csv"))
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, "File Type Incorrect");
        try (
                Reader reader = Files.newBufferedReader(Paths.get(IndianStateCodeFilePath));
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> csvIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusStateMap.get(csvState.getStateName()) != null)
                    .forEach(csvState -> censusStateMap.get(csvState.getStateName())
                            .stateCode = csvState.getStateCode());
            return censusStateMap;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, "No Such File Exists");
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.getMessage());
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect. Error While Building CSV.");
        }
    }
}
