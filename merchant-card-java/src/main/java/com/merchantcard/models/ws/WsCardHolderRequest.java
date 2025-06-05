package com.merchantcard.models.ws;

import com.merchantcard.models.APApiBaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WsCardHolderRequest extends APApiBaseRequest {
    /**
     * 产品ID,来自产品列表
     */
    private Integer bankcardId;

    private String requestOrderId;

    private Long holderId;

    /**
     * 手机区号。[2...5]位
     */
    private String areaCode;
    /**
     * 手机号。[5...20]位
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 名。只支持英文字符
     */
    private String firstName;
    /**
     * 姓。只支持英文字符
     */
    private String lastName;
    /**
     * 出生日期。yyyy-MM-dd
     */
    private String birthday;
    /**
     * 国家/地区代码。
     */
    private String country;
    /**
     * 城市代码。
     */
    private String town;
    /**
     * 地址。
     */
    private String address;

    /**
     * 邮编
     */
    private String postCode;
}
