package com.merchantcard.models;

import lombok.Data;

@Data
public class QueryBankcardBalanceRequest extends APApiBaseRequest {
    private Integer userBankcardId;
}
