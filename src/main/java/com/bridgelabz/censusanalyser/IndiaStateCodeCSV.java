package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {
    @CsvBindByName(column = "SrNo", required = true)
    private int srNo;
    @CsvBindByName(column = "StateName", required = true)
    private String stateName;
    @CsvBindByName(column = "TIN", required = true)
    private int tin;
    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;

    public String getStateName() {
        return stateName;
    }

    public String getStateCode() {
        return stateCode;
    }
}