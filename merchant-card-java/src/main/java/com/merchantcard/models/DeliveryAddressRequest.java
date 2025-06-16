package com.merchantcard.models;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class DeliveryAddressRequest extends APApiBaseRequest {

    private Integer id;
    /**
     * 国家ID
     */
    private Integer countryRegionId;

    /**
     * 城市
     */
    private String city;

    /**
     * 接受人
     */
    private String receiverName;

    /**
     * 接受人电话
     */
    private String receiverMobile;

    /**
     * 邮寄地址
     */
    private String receiverAddress;

    /**
     * 邮编
     */
    private String postCode;
}
