package com.merchantcard.models;

import lombok.Data;

@Data
public class UpdateBankcardStatusRequest extends APApiBaseRequest {
    private Integer userBankcardId;
    private boolean enable;
}
