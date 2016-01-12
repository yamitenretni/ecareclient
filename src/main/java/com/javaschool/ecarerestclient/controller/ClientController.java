package com.javaschool.ecarerestclient.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.javaschool.ecarerestclient.dto.*;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@ManagedBean
@SessionScoped
public class ClientController {
    private long tariff;
    private String tariffName;
    private List<ClientDTO> clientList;

    private boolean displayTable;

    public long getTariff() {
        return tariff;
    }

    public void setTariff(long tariff) {
        this.tariff = tariff;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public boolean isDisplayTable() {
        return displayTable;
    }

    public String loadReport() {
        String url = "http://localhost:8090/eCare/api/tariff/" + tariff + "/clients";
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        clientList = new ArrayList<>();

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.register(JsonProcessingFeature.class).target(url);

        JsonArray jsonArray = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(JsonArray.class);

        for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
            JsonObject user = jsonObject.getJsonObject("user");
            String firstName = user.getString("firstName");
            String lastName = user.getString("lastName");
            String email = user.getString("login");
            Date blockingDate = null;
            try {
                blockingDate = new Date(jsonObject.getJsonNumber("blockingDate").longValue());
            } catch (Exception e) {
            }
            Calendar birthDate = Calendar.getInstance();
            try {
                birthDate.setTimeInMillis(jsonObject.getJsonNumber("birthDate").longValue());
            } catch (Exception e) {
                birthDate = null;
            }
            String passportData = jsonObject.getString("passportData");
            String address = jsonObject.getString("address");
            List<ContractDTO> contractList = new ArrayList<>();
            JsonArray contractArray = jsonObject.getJsonArray("contracts");
            for (JsonObject contractObject : contractArray.getValuesAs(JsonObject.class)) {
                String number = contractObject.getString("number");
                String contractTariff = contractObject.getJsonObject("tariff").getString("name");
                Date contractBlockingDate = null;
                try {
                    contractBlockingDate = new Date(jsonObject.getJsonNumber("blockingDate").longValue());
                } catch (Exception e) {
                }
                contractList.add(new ContractDTO(number, contractTariff, contractBlockingDate));
            }
            ClientDTO clientDTO = new ClientDTO(firstName, lastName, email, birthDate, blockingDate, passportData, address, contractList);
            clientList.add(clientDTO);
        }
        displayTable = true;
        return "success";
    }

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
    }
}
