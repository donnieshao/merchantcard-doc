package com.merchantcard.models;

import lombok.Data;

@Data
public class UpdateWHolderInfoRequest extends APApiBaseRequest{

    private Integer bankCardId;

    private String mobilePrefix;

    private String mobile;
}
