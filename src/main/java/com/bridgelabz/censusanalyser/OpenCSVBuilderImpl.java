package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilderImpl<E> implements ICSVBuilder {
    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBean<E> csvToBean = this.getCSVBean(reader, csvClass);
            Iterator<E> censusCSVIterator = csvToBean.iterator();
            return censusCSVIterator;
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect. Error While Building CSV.");
        }
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBean<E> csvToBean = this.getCSVBean(reader, csvClass);
            List<E> censusCSVList = csvToBean.parse();
            return censusCSVList;
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect. Error While Building CSV.");
        }
    }

    private <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true);
        return csvToBeanBuilder.build();
    }
}
