package com.merchantcard.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplyBankcardV2Request extends APApiBaseRequest {
    private Integer templateId;
    private Integer deliveryAddressId;
}
