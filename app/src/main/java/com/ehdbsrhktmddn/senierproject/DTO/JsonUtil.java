package com.ehdbsrhktmddn.senierproject.DTO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.LocalDateTime;

public class JsonUtil {

    private static JSONParser parser = new JSONParser();

    public static JSONObject string_To_Json(String message) {

        JSONObject result = null;
        try {
            result = (JSONObject) parser.parse(message);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArduinoVO json_To_ArduinoVO(JSONObject json) {

        ArduinoVO vo = new ArduinoVO();

        try {
            vo.setDate((LocalDateTime) json.get("Date"));
            vo.setMAC_ar((String) json.get("MAC"));

            JSONObject sensors = (JSONObject) json.get("Sensors");

            vo.setSensor_1((double) sensors.get("sensor1"));
            vo.setSensor_2((double) sensors.get("sensor2"));
            vo.setSensor_3((double) sensors.get("sensor3"));
        } catch(Exception e) {


        }
        return vo;
    }
}
