package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvLoaderClass) throws CensusAnalyserException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvLoaderClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<E> csvStatesIterator = csvToBean.iterator();
            return csvStatesIterator;
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect");
        }
    }
}
