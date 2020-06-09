package com.ehdbsrhktmddn.senierproject.DTO;

import org.json.JSONObject;

public class AndroidVO {

    final private String type = "Android";

    private String MAC_ad;
    private String reqeust_type;


    public String getType() {
        return type;
    }

    public String getMAC_ad() {
        return MAC_ad;
    }

    public void setMAC_ad(String MAC_ad) {
        this.MAC_ad = MAC_ad;
    }

    public String getReqeust_type() {
        return reqeust_type;
    }

    public void setReqeust_type(String reqeust_type) {
        this.reqeust_type = reqeust_type;
    }

    public JSONObject androidVO_TO_JSON() {

        JSONObject json = null;
        try{
            json = new JSONObject();
            json.put("type", type);
            json.put("MAC", MAC_ad);
            json.put("request_type", reqeust_type);
        }
        catch (Exception e) {

        }

        return json;
    }
}
