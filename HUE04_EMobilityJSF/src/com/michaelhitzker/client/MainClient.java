package com.michaelhitzker.client;

import java.util.HashMap;

public class MainClient {
    public static void main(String[] args){
        EmobilityClient client = new EmobilityClient("http://127.0.0.1:8182/api");
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", "1");
        params.put("stationId", "1");
        client.getCharges(params);
    }
}
