package com.bridgelabz.censusanalyser;

import com.bridgelabz.csvbuilder.CSVBuilderException;
import com.bridgelabz.csvbuilder.CSVBuilderFactory;
import com.bridgelabz.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    public enum SortingMode {STATE, STATECODE, POPULATION, DENSITY, AREA}

    Map<String, CensusDAO> censusStateMap = null;

    public StateCensusAnalyser() {
        this.censusStateMap = new HashMap<String, CensusDAO>();
    }

    public static void main(String[] args) {
        System.out.println("Welcome To Census Analyser Problem");
    }

    public int loadIndiaCensusData(String csvPath) throws CensusAnalyserException {
        censusStateMap = new CensusLoader().loadCensusData(csvPath, IndiaCensusCSV.class);
        return censusStateMap.size();
    }

    public int loadIndiaStateCode(String csvPath) throws CensusAnalyserException {
        int lastIndexOf = csvPath.lastIndexOf(".");
        String fileExtension = (lastIndexOf == -1) ? "" : csvPath.substring(lastIndexOf);
        if (!fileExtension.equals(".csv"))
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, "File Type Incorrect");
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvPath));
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> csvIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusStateMap.get(csvState.getStateName()) != null)
                    .forEach(csvState -> censusStateMap.get(csvState.getStateName())
                            .stateCode = csvState.getStateCode());
            return this.censusStateMap.size();
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, "No Such File Exists");
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.getMessage());
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect. Error While Building CSV.");
        }
    }

    public int loadUSCensusData(String csvPath) throws CensusAnalyserException {
        censusStateMap = new CensusLoader().loadCensusData(csvPath, USCensusCSV.class);
        return censusStateMap.size();
    }

    public String getSortedCensusData(SortingMode mode) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> censusCSVComparator = CensusDAO.getSortComparator(mode);
        List<CensusDAO> censusDAOS = censusStateMap.values().stream().collect(Collectors.toList());
        this.sort(censusCSVComparator, censusDAOS);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    public void sort(Comparator<CensusDAO> censusCSVComparator, List<CensusDAO> censusDAOS) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                CensusDAO census1 = censusDAOS.get(j);
                CensusDAO census2 = censusDAOS.get(j + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    censusDAOS.set(j, census2);
                    censusDAOS.set(j + 1, census1);
                }
            }
        }
    }
}