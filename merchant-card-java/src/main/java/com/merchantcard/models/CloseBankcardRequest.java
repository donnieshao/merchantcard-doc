package com.merchantcard.models;

import lombok.Data;

@Data
public class CloseBankcardRequest extends APApiBaseRequest {
    private Integer userBankcardId;
}
