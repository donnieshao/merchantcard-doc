package com.merchantcard.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UCardHolderDeliveryAddressData {
    private String country;
    private String city;
    private String state;
    private String line1;
    private String line2;
    private String postal_code;
}
