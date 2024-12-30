package com.merchantcard.models;

import com.merchantcard.constants.IDTypes;
import lombok.Data;

import java.io.Serializable;

@Data
public class UCardHolderIdentificationVo implements Serializable {
    private IDTypes identificationType;
    // 证件号码
    private String identificationNumber;
    // 证件到期时间
    private String identificationExpiryDate;
    // 正面图片
    private String frontImgFileId;
    // 反面图片
    private String backImgFileId;
    // 手持图片
    private String handheldImgFileId;
}
