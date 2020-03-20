package com.bridgelabz.censusanalyser;

import java.io.Reader;
import java.util.List;

public interface ICSVBuilder {
    public <E> List getCSVFileList(Reader reader, Class<E> csvLoaderClass) throws CSVBuilderException;
}
