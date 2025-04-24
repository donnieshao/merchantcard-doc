package com.merchantcard.constants;

public class MerchantCardMethods {

    // system status
    public static String SYS_CLOCK = "/sys/clock";

    // bankcard info list
    public static String BANKCARD_TEMPLATE_LIST = "/bankcard/template/list";

    // user register
    public static String USER_REGISTER = "/user/register";

    // apply the bankcard
    public static String APPLY_BANKCARD = "/bankcard/apply";

    // recharge the bankcard
    public static String RECHARGE_BANKCARD = "/bankcard/recharge";

    // set card atm pin
    public static String SET_BANKCARD_PIN = "/bankcard/setPin";

    public static String GET_PIN = "/bankcard/getPin";

    // query bankcard transactions
    public static String QUERY_BANKCARD_TRANSACTIONS = "/bankcard/transactions";

    // query bankcard balance
    public static String QUERY_BANKCARD_BALANCE = "/bankcard/balance";

    // query bankcard info
    public static String QUERY_BANKCARD_INFO = "/bankcard/cardInfo";

    // 商户资产信息
    public static String MERCHANT_ASSET = "/merchant/asset";

    public static String MERCHANT_RECHARGE_INFO = "/merchant/rechargeInfo";

    public static String MERCHANT_HISTORY_LOGS = "/merchant/HistoryLogs";

    public static String MERCHANT_RECHARGE_RECORDS = "/merchant/rechargeList";

    public static String ACTIVE_CARDS ="/bankcard/activatecard";

    // update bankcard status
    public static String UPDATE_CARD_STATUS = "/bankcard/update/status";

    // close bankcard
    public static String CLOSE_CARD = "/bankcard/close";

    public static String CARD_ORDER = "/bankcard/cardOrderInfo";

    public static String SET_USER_INFO = "/user/setUserInfo";

    public static String KYC_CHECK = "/user/kycCheck";

    public static String KYC_STATUS = "/user/kycStatus";

    public static String APPROVE_3DSAUTH = "/bankcard/approve/3dsAuth";

    public static String REJECT_3DSAUTH = "/bankcard/reject/3dsAuth";

    public static String QUERY_3DSAUTH = "/bankcard/query/3dsAuth";

    public static String USD_TO_EUR = "/bankcard/getEURAmount";

    public static String USD_RECHARGE = "/user/usdRechargeInfo";



    /*************UCards***************/
    public static String UCARD_KYC_APPLY = "/UCard/kyc/apply";

    public static String UCARD_KYC_STATUS = "/UCard/kyc/status";

    public static String UCARD_ASSIGN_CARD = "/UCard/assign";

    public static String UCARD_ACTIVATE_CARD = "/UCard/active";

    public static String UCARD_UPLOAD_FILE = "/upload/uCardFile";


    /**************Wholder***************/
    public static String SETWHolderInfo = "/user/setWHolderInfo";

    /*************WSCards***************/
    public static String WSCARD_LIST_REGION = "/WSCard/holder/region/list";

    public static String WSCARD_LIST_CITY = "/WSCard/holder/city/list";

    public static String WSCARD_MOBILE_AREA = "/WSCard/holder/mobile/area/list";


    /**************WS***************/
    public static String CREATE_WS_CARDHOLDER = "/WSCard/holder/apply";

    public static String UPDATE_WS_CARDHOLDER = "/WSCard/holder/update";

    public static String QUERY_WS_CARDHOLDER = "/WSCard/holder/query";

}
