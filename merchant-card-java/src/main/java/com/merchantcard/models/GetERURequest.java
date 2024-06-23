package com.merchantcard.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetERURequest extends APApiBaseRequest {
    private BigDecimal amount;

}
