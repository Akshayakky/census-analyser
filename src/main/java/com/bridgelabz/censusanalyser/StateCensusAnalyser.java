package com.bridgelabz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StateCensusAnalyser {

    public static void main(String[] args) {
        System.out.println("Welcome To Census Analyser Problem");
    }

    public int loadCSV(String csvPath, Class csvLoaderClass) throws CensusAnalyserException {
        int lastIndexOf = csvPath.lastIndexOf(".");
        String fileExtension = (lastIndexOf == -1) ? "" : csvPath.substring(lastIndexOf);
        if (!fileExtension.equals(".csv"))
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, "File Type Incorrect");
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvPath));
        ) {
            ICSVBuilder icsvBuilder = new OpenCSVBuilder();
            List csvList = icsvBuilder.getCSVFileList(reader, csvLoaderClass);
            return csvList.size();
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, "No Such File Exists");
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.getMessage());
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect. Error While Building CSV.");
        }
    }
}