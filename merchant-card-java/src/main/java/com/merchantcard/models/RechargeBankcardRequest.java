package com.merchantcard.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RechargeBankcardRequest extends APApiBaseRequest {
    private Integer userBankcardId;
    private BigDecimal amount;
    private BigDecimal targetAmount;
}
