package com.merchantcard.models;

import lombok.Data;

@Data
public class QueryMerchantHistoryLogsRequest extends APApiBaseRequest {
    private Integer pageNum;
    private Integer pageSize;
    private Long fromTimestamp;
    private Long endTimestamp;
}
