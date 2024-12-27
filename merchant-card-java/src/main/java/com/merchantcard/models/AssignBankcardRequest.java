package com.merchantcard.models;

import lombok.Data;

@Data
public class AssignBankcardRequest extends APApiBaseRequest{
    private String cardNumber;
    private String cardCurrency;
}
