package com.merchantcard.models;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class DeliveryListRequest extends APApiBaseRequest {

    private Integer id;

}
