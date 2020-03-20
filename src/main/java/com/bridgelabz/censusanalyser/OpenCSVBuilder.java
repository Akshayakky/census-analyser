package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class OpenCSVBuilder implements ICSVBuilder{
    @Override
    public <E> List getCSVFileList(Reader reader, Class<E> csvLoaderClass) throws CSVBuilderException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvLoaderClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return  csvToBean.parse();
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect. Error While Building CSV.");
        }
    }
}
