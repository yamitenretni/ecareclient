package com.javaschool.ecarerestclient.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Лена on 05.01.2016.
 */
public class ClientDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Calendar birthDate;
    private Date blockingDate;
    private String passportData;
    private String address;
    private List<ContractDTO> contractList;

    public ClientDTO() {
        contractList = new ArrayList<>();
    }

    public ClientDTO(String firstName, String lastName, String email, Calendar birthDate, Date blockingDate, String passportData, String address, List<ContractDTO> contractList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.blockingDate = blockingDate;
        this.passportData = passportData;
        this.address = address;
        this.contractList = contractList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBlockingDate() {
        return blockingDate;
    }

    public void setBlockingDate(Date blockingDate) {
        this.blockingDate = blockingDate;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ContractDTO> getContractList() {
        return contractList;
    }

    public void setContractList(List<ContractDTO> contractList) {
        this.contractList = contractList;
    }
}
