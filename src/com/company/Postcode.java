package com.company;

import com.google.gson.JsonObject;

public class Postcode {
    private String postcode;
    private int quality;
    private int eastings;
    private int northings;
    private String country;
    private String nhs_ha;
    private float longitude;
    private float latitude;
    private String european_electoral_region;
    private String primary_care_trust;
    private String region;
    private String lsoa;
    private String msoa;
    private String incode;
    private String outcode;
    private String parliamentary_constituency;
    private String admin_district;
    private String parish;
    private String admin_county;
    private String admin_ward;
    private String ced;
    private String ccg;
    private String nuts;
    private JsonObject codes;

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }
}
