package com.javaschool.ecarerestclient.dto;

import java.util.Date;

/**
 * Created by Лена on 05.01.2016.
 */
public class ContractDTO {
    private String number;
    private String Tariff;
    private Date blockingDate;

    public ContractDTO(String number, String tariff, Date blockingDate) {
        this.number = number;
        Tariff = tariff;
        this.blockingDate = blockingDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTariff() {
        return Tariff;
    }

    public void setTariff(String tariff) {
        Tariff = tariff;
    }

    public Date getBlockingDate() {
        return blockingDate;
    }

    public void setBlockingDate(Date blockingDate) {
        this.blockingDate = blockingDate;
    }
}
