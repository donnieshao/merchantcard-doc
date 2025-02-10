package com.merchantcard.models;

import lombok.Data;

@Data
public class SetWHolderInfoRequest extends APApiBaseRequest {
    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String mobilePrefix;

    private String mobile;

    private String birthDate;

    private String countryCode;

    private String billingState;

    private String billingCity;

    private String billingAddress;

    private String billingZipCode;
}
