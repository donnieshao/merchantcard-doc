package com.merchantcard.models;

import lombok.Data;

@Data
public class UCardSetHolderInfoRequest extends APApiBaseRequest{

    private UCardHolderInfoVo holderInfo;

    private UCardDeliveryAddressVo deliveryAddress;

    private UCardHolderIdentificationVo identification;
}
