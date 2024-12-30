package com.merchantcard.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class UCardDeliveryAddressVo implements Serializable {
    private String country;
    private String city;
    private String state;
    private String line1;
    private String line2;
    private String postal_code;
}
