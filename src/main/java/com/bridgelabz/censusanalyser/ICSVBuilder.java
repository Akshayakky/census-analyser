package com.bridgelabz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvLoaderClass) throws CSVBuilderException;
}
