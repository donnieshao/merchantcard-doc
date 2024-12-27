package com.merchantcard.models;

import lombok.Data;

@Data
public class UCardSetHolderInfoRequest extends APApiBaseRequest{
    private String email;
    private String first_name;
    private String last_name;
    /**
     * yyyy-mm-dd
     */
    private String date_of_birth;
    /**
     * Two-letter country code
     */
    private String country_code;
    private String phone_number;

    private UCardDeliveryAddress delivery_address;

    private String document_type;

    private String document;
}
