package com.merchantcard.models;

import lombok.Data;

@Data
public class ActivateBankcardRequest extends APApiBaseRequest {
    private Integer userBankcardId;
    private String activationCode;
}
