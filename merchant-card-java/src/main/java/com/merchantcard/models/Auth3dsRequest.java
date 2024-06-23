package com.merchantcard.models;

import lombok.Data;

@Data
public class Auth3dsRequest extends APApiBaseRequest {
    private String authId;

}
