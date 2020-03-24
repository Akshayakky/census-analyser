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

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}