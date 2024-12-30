package com.merchantcard.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class UCardHolderInfoVo implements Serializable {
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

    private String document_type;

    private String document;
}
