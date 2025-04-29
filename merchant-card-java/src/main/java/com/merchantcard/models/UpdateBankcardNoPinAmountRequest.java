package com.merchantcard.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateBankcardNoPinAmountRequest extends APApiBaseRequest{

    private Integer userBankcardId;
    private String amount;
}
