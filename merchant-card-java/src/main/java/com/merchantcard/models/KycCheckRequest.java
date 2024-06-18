package com.merchantcard.models;

import lombok.Data;

@Data
public class KycCheckRequest extends APApiBaseRequest {
    private String idType;
    private String country;
}
