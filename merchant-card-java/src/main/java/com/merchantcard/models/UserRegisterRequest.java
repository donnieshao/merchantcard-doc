package com.merchantcard.models;

import lombok.Data;

@Data
public class UserRegisterRequest extends APApiBaseRequest {
    private String mobilePrefix;
    private String mobileNumber;
    private String email;
}
