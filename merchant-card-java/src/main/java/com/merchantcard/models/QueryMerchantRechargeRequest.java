package com.merchantcard.models;

import lombok.Data;

@Data
public class QueryMerchantRechargeRequest extends APApiBaseRequest {
    private Integer pageNum;
    private Integer pageSize;
}
