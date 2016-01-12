package com.javaschool.ecarerestclient.controller;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@RequestScoped
public class TariffController {

    private Long tariff;

    private Map<Long, String> tariffList = new HashMap<>();

    public Map<Long, String> getTariffList() {
        String url = "http://localhost:8090/eCare/api/tariff";

            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.register(JsonProcessingFeature.class).target(url);
            JsonArray jsonArray = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(JsonArray.class);

            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                tariffList.put(jsonObject.getJsonNumber("id").longValue(), jsonObject.getString("name"));
            }
        return tariffList;
    }

    public void setTariffList(Map<Long, String> tariffList) {
        this.tariffList = tariffList;
    }

    public Long getTariff() {
        return tariff;
    }

    public void setTariff(Long tariff) {
        this.tariff = tariff;
    }
}
