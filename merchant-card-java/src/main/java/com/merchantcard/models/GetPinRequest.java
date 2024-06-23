package com.merchantcard.models;

import lombok.Data;

@Data
public class GetPinRequest extends APApiBaseRequest {
    private Integer userBankcardId;

}
