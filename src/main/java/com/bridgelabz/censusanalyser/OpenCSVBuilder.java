package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.List;

public class OpenCSVBuilder implements ICSVBuilder{
    @Override
    public List getCSVFileList(Reader reader, Class csvLoaderClass) throws CSVBuilderException {
        try {
            List csvList = new CsvToBeanBuilder(reader)
                    .withType(csvLoaderClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build().parse();
            return  csvList;
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "Delimiter Or Header Incorrect. Error While Building CSV.");
        }
    }
}
