package com.merchantcard.models;

import lombok.Data;

@Data
public class ActivateBankcardRequest extends APApiBaseRequest {
    private String cardId;
    private String activationCode;
}
