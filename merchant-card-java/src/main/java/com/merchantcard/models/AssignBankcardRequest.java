package com.merchantcard.models;

import lombok.Data;

@Data
public class AssignBankcardRequest extends APApiBaseRequest {
    private String cardNumber;
    private Integer bankcardId;
    private boolean autoActive;
}
