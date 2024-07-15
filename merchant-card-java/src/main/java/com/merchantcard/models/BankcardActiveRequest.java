package com.merchantcard.models;

import lombok.Data;

@Data
public class BankcardActiveRequest extends APApiBaseRequest {
    private Integer templateId;
    private String cardNo;
}
