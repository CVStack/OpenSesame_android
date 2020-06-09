package com.ehdbsrhktmddn.senierproject.DTO;

import org.json.JSONObject;

import java.time.LocalDateTime;

public class ArduinoVO {

    private final String type = "Arduino";
    private String MAC_ar;
    private double sensor_1; //센서 종류 1
    private double sensor_2; //센서 종류 2
    private double sensor_3; //센서 종류 3

    private LocalDateTime date;

    public static ArduinoVO json_To_ArduinoVO(JSONObject json){

        ArduinoVO vo = new ArduinoVO();

        return vo;
    }

    public String getType() {
        return type;
    }

    public double getSensor_1() {
        return sensor_1;
    }

    public void setSensor_1(double sensor_1) {
        this.sensor_1 = sensor_1;
    }

    public double getSensor_2() {
        return sensor_2;
    }

    public void setSensor_2(double sensor_2) {
        this.sensor_2 = sensor_2;
    }

    public double getSensor_3() {
        return sensor_3;
    }

    public void setSensor_3(double sensor_3) {
        this.sensor_3 = sensor_3;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMAC_ar() {
        return MAC_ar;
    }

    public void setMAC_ar(String MAC_ar) {
        this.MAC_ar = MAC_ar;
    }

}
