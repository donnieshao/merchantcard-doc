package com.merchantcard.models;

import lombok.Data;

@Data
public class SetBankcardPinRequest extends APApiBaseRequest {
    private Integer userBankcardId;
    private String pin;
}
