package com.merchantcard.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DeerTransferOrderRequest extends APApiBaseRequest {

    private Integer id;
    /**
     * 用户Id
     */
    private Integer customerId;

    private String email;
    /**
     * 收款账号或支付宝手机号
     */
    private String  receivePaymentAccountNumber;

    /**
     * 收款人手机号 ，transferType=1,必填
     */
    private String  mobile;

    /**
     * 收款人身份证号 ，transferType=1,必填
     */
    private String  identityNo;
    /**
     * 名
     */
    private String firstName;
    /**
     * 姓
     */
    private String lastName;

    /***
     * 转账金额
     */
    private BigDecimal amount;

    /**
     * 到账金额
     */
    private BigDecimal arrivalAmount;

    /**
     * 转账类型，0，支付宝，1，银行
     */
    private Integer transferType;

    /**
     * usdt数量
     */
    private BigDecimal usdtAmount;

    /**
     * 扣款方式，0，账户，1，实体卡
     */
    private Integer deductType;

    /**
     * 扣款实体卡号，deductType=1 必传
     */
    private String cardNo;
    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0 待汇款，1 汇款成功，2.汇款失败，3.汇款处理中
     */
    private Integer status;
    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 汇款订单号
     */
    private String orderNo;
}
