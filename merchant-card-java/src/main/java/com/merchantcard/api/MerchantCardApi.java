package com.merchantcard.api;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.merchantcard.constants.IDTypes;
import com.merchantcard.constants.MerchantCardMethods;
import com.merchantcard.models.ApiResponse;
import com.merchantcard.models.APApiBaseRequest;
import com.merchantcard.models.*;
import com.merchantcard.models.SystemClockRequest;
import com.merchantcard.utils.APEncryptUtil;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
public class MerchantCardApi {

    // test env gateway
    private static final String GATEWAY = "https://test.asinx.io/api-web";

    // APPID
    private static final String APP_ID = "";

    // SECRET
    private static String APP_SECRET = "";


    private static final int NOTIFY_TIMEOUT = 15000;

    private static final int NOTIFY_CONNECT_TIMEOUT = 1000;

    // if use proxy ,set this value true
    private static boolean useProxy = true;

    // proxy ip
    private static String proxyAddress = "127.0.0.1";

    // proxy port
    private static int proxyPort = 7070;


    /**
     * get system clock(system status)
     */
    public static void getSystemClock() {

        SystemClockRequest request = new SystemClockRequest();
        String result = postData(null, MerchantCardMethods.SYS_CLOCK, request, null);
        log.info("getSystemClock response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        log.info("getSystemClock response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            log.info("getSystemClock encode===>" + descStr);
        }
    }

    /**
     * get template list
     */
    public static void bankcardTemplateList() {
        BankcardTemplateListRequest request = new BankcardTemplateListRequest();
        String result = postData(null, MerchantCardMethods.BANKCARD_TEMPLATE_LIST, request, null);
        System.out.println("bankcardTemplateList response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("bankcardTemplateList response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("bankcardTemplateList encode result===>" + descStr);
        }
    }

    public static void merchantRechargeInfo() {
        BankcardRechargeInfoRequest request = new BankcardRechargeInfoRequest();
        String result = postData(null, MerchantCardMethods.MERCHANT_RECHARGE_INFO, request, null);
        System.out.println("merchantRechargeInfo response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("merchantRechargeInfo response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("merchantRechargeInfo encode result===>" + descStr);
        }
    }

    /**
     * user register,get user unique ID
     *
     * @param mobilePrefix mobile prefix
     * @param mobileNumber mobile number
     */
    public static void userRegister(String mobilePrefix, String mobileNumber, String email) {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setMobilePrefix(mobilePrefix);
        request.setMobileNumber(mobileNumber);
        request.setEmail(email);
        String result = postData(null, MerchantCardMethods.USER_REGISTER, request, null);
        System.out.println("userRegister response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("userRegister response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("userRegister encode result===>" + descStr);
        }
    }

    public static void setWHolderInfo(String uId) {
        SetWHolderInfoRequest request = new SetWHolderInfoRequest();
        request.setFirstName("dongdong");
//        request.setMiddleName("1");
        request.setLastName("shao");
        request.setBirthDate("1990-01-01");
        request.setMobilePrefix("86");
        request.setMobile("13561790246");
        request.setEmail("2644632411@qq.com");
        request.setCountryCode("CHN");

        request.setBillingState("beijing");
        request.setBillingCity("beijing");
        request.setBillingAddress("chaoyang qu,jianguo road,100");
        request.setBillingZipCode("10010");

        String result = postData(uId, MerchantCardMethods.SETWHolderInfo, request, null);
        System.out.println("setWHolderInfo response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("setWHolderInfo response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("setWHolderInfo encode result===>" + descStr);
        }
    }

    /**
     * apply bankcard
     *
     * @param uId
     * @param bankcardId
     * @param residenceAddress
     */
    public static Integer applyBankcard(String uId, Integer bankcardId, Integer userBankcardId, String residenceAddress) {
        ApplyBankcardRequest request = new ApplyBankcardRequest();
        request.setBankcardId(bankcardId);
        request.setResidenceAddress(residenceAddress);
        String result = postData(uId, MerchantCardMethods.APPLY_BANKCARD, request, null);
        System.out.println("applyBankcard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("applyBankcard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("applyBankcard encode result===>" + descStr);

            JSONObject json = JSON.parseObject(descStr);
            return json.getInteger("userBankcardId");
        }
        return null;
    }

    /**
     * recharge bankcard
     *
     * @param uId
     * @param userBankcardId
     * @param amount
     */
    public static void rechargeBankcard(String uId, Integer userBankcardId, BigDecimal amount, BigDecimal targetAmount) {
        RechargeBankcardRequest request = new RechargeBankcardRequest();
        request.setUserBankcardId(userBankcardId);
        request.setAmount(amount);
        request.setTargetAmount(targetAmount);
        String result = postData(uId, MerchantCardMethods.RECHARGE_BANKCARD, request, UUID.randomUUID().toString());
        System.out.println("rechargeBankcard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("rechargeBankcard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("success rechargeBankcard encode result===>" + descStr);
        } else {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("failed rechargeBankcard encode result===>" + descStr);
        }
    }

    /**
     * query bankcard transactions
     *
     * @param uId
     * @param userBankcardId
     */
    public static void queryBankcardTransactions(String uId, Integer userBankcardId) {
        QueryBankcardTransactionsRequest request = new QueryBankcardTransactionsRequest();
        request.setUserBankcardId(userBankcardId);
//        request.setFromTimestamp(1729440000000L);
//        request.setEndTimestamp(1729871999000L);
        request.setPageSize(100);
        request.setPageNum(1);
        String result = postData(uId, MerchantCardMethods.QUERY_BANKCARD_TRANSACTIONS, request, null);
        System.out.println("queryBankcardTransactions response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("queryBankcardTransactions response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("queryBankcardTransactions encode result===>" + descStr);
        }
    }

    /**
     * query bankcard transactions
     *
     * @param uId
     * @param userBankcardId
     */
    public static void queryBankcardBalance(String uId, Integer userBankcardId) {
        QueryBankcardBalanceRequest request = new QueryBankcardBalanceRequest();
        request.setUserBankcardId(userBankcardId);
        String result = postData(uId, MerchantCardMethods.QUERY_BANKCARD_BALANCE, request, null);
        System.out.println("queryBankcardBalance response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("queryBankcardBalance response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("queryBankcardBalance encode result===>" + descStr);
        }
    }

    /**
     * query bankcard Information
     *
     * @param uId
     * @param userBankcardId
     */
    public static void queryBankcardInfo(String uId, Integer userBankcardId) {
        QueryBankcardInfoRequest request = new QueryBankcardInfoRequest();
        request.setUserBankcardId(userBankcardId);
        String result = postData(uId, MerchantCardMethods.QUERY_BANKCARD_INFO, request, null);
        System.out.println("queryBankcardInfo response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("queryBankcardInfo response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("queryBankcardInfo encode result===>" + descStr);
        }
    }

    public static void queryBankcardOrder(String uId, Integer userBankcardId, String requestOrderId) {
        QueryBankcardOrderRequest request = new QueryBankcardOrderRequest();
        request.setUserBankcardId(userBankcardId);
        request.setRequestOrderId(requestOrderId);
        String result = postData(uId, MerchantCardMethods.CARD_ORDER, request, null);
        System.out.println("queryBankcardOrder response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("queryBankcardOrder response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("queryBankcardOrder encode result===>" + descStr);
        }
    }

    public static void merchantAsset() {
        QueryMerchantAssetRequest request = new QueryMerchantAssetRequest();
        String result = postData(null, MerchantCardMethods.MERCHANT_ASSET, request, null);
        System.out.println("merchantAsset response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("merchantAsset response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("accountRecharge encode result===>" + descStr);
        }
    }

    public static void merchantHistoryLogs() {
        QueryMerchantHistoryLogsRequest request = new QueryMerchantHistoryLogsRequest();
        request.setPageSize(10);
        request.setEndTimestamp(1730300532000L);
        request.setFromTimestamp(1730299387000L);
        request.setPageNum(1);
        String result = postData(null, MerchantCardMethods.MERCHANT_HISTORY_LOGS, request, null);
        System.out.println("merchantHistoryLogs response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("merchantHistoryLogs response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("merchantHistoryLogs encode result===>" + descStr);
        }
    }

    public static void merchantRechargeRecords() {
        QueryMerchantHistoryLogsRequest request = new QueryMerchantHistoryLogsRequest();
        request.setPageSize(10);
        request.setPageNum(1);
        String result = postData(null, MerchantCardMethods.MERCHANT_RECHARGE_RECORDS, request, null);
        System.out.println("merchantRechargeRecords response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("merchantRechargeRecords response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("merchantRechargeRecords encode result===>" + descStr);
        }
    }

    public static void activeBankcard(String uId, Integer templateId, String cardNo) {
        BankcardActiveRequest request = new BankcardActiveRequest();
        request.setTemplateId(templateId);
        request.setCardNumber(cardNo);

        String result = postData(uId, MerchantCardMethods.ACTIVE_CARDS, request, null);
        System.out.println("ActiveBankcard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("ActiveBankcard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("ActiveBankcard encode result===>" + descStr);
        }
    }

    public static void updateBankcardStatus(String uId, Integer userBankcardId, boolean enable) {
        UpdateBankcardStatusRequest request = new UpdateBankcardStatusRequest();
        request.setEnable(enable);
        request.setUserBankcardId(userBankcardId);

        String result = postData(uId, MerchantCardMethods.UPDATE_CARD_STATUS, request, null);
        System.out.println("updateBankcardStatus response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("updateBankcardStatus response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("updateBankcardStatus encode result===>" + descStr);
        }
    }

    public static void closeBankcard(String uId, Integer userBankcardId) {
        CloseBankcardRequest request = new CloseBankcardRequest();
        request.setUserBankcardId(userBankcardId);

        String result = postData(uId, MerchantCardMethods.CLOSE_CARD, request, null);
        System.out.println("closeBankcard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("closeBankcard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("closeBankcard encode result===>" + descStr);
        }
    }

    public static void kycCheck(String uId) {
        KycCheckRequest request = new KycCheckRequest();
        request.setIdType("PASSPORT");
        request.setCountry("CN");
        String result = postData(uId, MerchantCardMethods.KYC_CHECK, request, null);
        System.out.println("kycCheck response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("kycCheck response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("kycCheck encode result===>" + descStr);
        }
    }

    public static void kycStatus(String uId) {
        APApiBaseRequest request = new APApiBaseRequest() {
        };
        String result = postData(uId, MerchantCardMethods.KYC_STATUS, request, null);
        System.out.println("kycStatus response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("kycStatus response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("kycStatus encode result===>" + descStr);
        }
    }

    public static void query3dsAuth(String authId, String uId) {
        Auth3dsRequest request = new Auth3dsRequest();
        request.setAuthId(authId);
        String result = postData(uId, MerchantCardMethods.QUERY_3DSAUTH, request, null);
        System.out.println("query3dsAuth response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("query3dsAuth response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("query3dsAuth encode result===>" + descStr);
        }
    }

    public static void approve3dsAuth(String authId, String uId) {
        Auth3dsRequest request = new Auth3dsRequest();
        request.setAuthId(authId);
        String result = postData(uId, MerchantCardMethods.APPROVE_3DSAUTH, request, null);
        System.out.println("approve3dsAuth response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("approve3dsAuth response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("approve3dsAuth encode result===>" + descStr);
        }
    }

    public static void reject3dsAuth(String authId, String uId) {
        Auth3dsRequest request = new Auth3dsRequest();
        request.setAuthId(authId);
        String result = postData(uId, MerchantCardMethods.REJECT_3DSAUTH, request, null);
        System.out.println("reject3dsAuth response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("reject3dsAuth response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("reject3dsAuth encode result===>" + descStr);
        }
    }

    public static void getPin(Integer userBankcardId, String uId) {
        GetPinRequest request = new GetPinRequest();
        request.setUserBankcardId(userBankcardId);
        String result = postData(uId, MerchantCardMethods.GET_PIN, request, null);
        System.out.println("getPin response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("getPin response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("getPin encode result===>" + descStr);
        }
    }

    public static void usdToEur(BigDecimal amount, String uId) {
        GetERURequest request = new GetERURequest();
        request.setAmount(amount);
        String result = postData(uId, MerchantCardMethods.USD_TO_EUR, request, null);
        System.out.println("usdToEur response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("usdToEur response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("usdToEur encode result===>" + descStr);
        }
    }

    /**
     * payment gateway
     *
     * @param uId
     * @param amount
     */
    public static void usdRecharge(String uId, BigDecimal amount) {
        UserRechargeInfoRequest request = new UserRechargeInfoRequest();
        request.setAmount(amount);
        String result = postData(uId, MerchantCardMethods.USD_RECHARGE, request, null);
        System.out.println("usdRecharge response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("usdRecharge response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("usdRecharge encode result===>" + descStr);
        }
    }


    public static void ucardUploadFile(String uId) {
        String result = postFormFile(uId, "uCardFile", "/Users/donnie/merchantcard-doc/merchant-card-java/src/main/resources/passport2.jpg", MerchantCardMethods.UCARD_UPLOAD_FILE);
        System.out.println("ucardUploadFile response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("ucardUploadFile response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("ucardUploadFile encode result===>" + descStr);
        }
    }


    public static void uCardKYCApply(String uId) {
        UCardDeliveryAddressVo address = new UCardDeliveryAddressVo();
        UCardHolderInfoVo holderInfo = new UCardHolderInfoVo();
        holderInfo.setFirst_name("apppaha");
        holderInfo.setLast_name("sharessaa");
        holderInfo.setCountry_code("CN");
        holderInfo.setDate_of_birth("1987-11-01");
        holderInfo.setPhone_number("18318191661");
        holderInfo.setEmail("test_uq_group_force_recharge@gmail.com");
        UCardHolderIdentificationVo identificationVo = new UCardHolderIdentificationVo();
        identificationVo.setIdentificationType(IDTypes.PASSPORT);
        identificationVo.setIdentificationNumber("1234561789");
        identificationVo.setIdentificationExpiryDate("2029-01-01");
        String demoImg = "3e3d0798-e9c6-49c9-bdb9-612e29b56d2d";
        identificationVo.setFrontImgFileId(demoImg);
        identificationVo.setBackImgFileId(demoImg);
        identificationVo.setHandheldImgFileId(demoImg);
        UCardSetHolderInfoRequest request = new UCardSetHolderInfoRequest();
        request.setHolderInfo(holderInfo);
//        request.setAttemptBankcardId(121);
//        request.setDeliveryAddress(address);
        request.setIdentification(identificationVo);

        String result = postData(uId, MerchantCardMethods.UCARD_KYC_APPLY, request, null);
        System.out.println("setUCardHolderInfo response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("setUCardHolderInfo response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("setUCardHolderInfo encode result===>" + descStr);
        }
    }

    public static void uCardKycStatus(String uId) {
        APApiBaseRequest request = new APApiBaseRequest() {
        };
        String result = postData(uId, MerchantCardMethods.UCARD_KYC_STATUS, request, null);
        System.out.println("uCardkycStatus response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("uCardkycStatus response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("uCardkycStatus encode result===>" + descStr);
        }
    }

    /**
     * assign card
     *
     * @param
     */
    public static void assignCard(String uId,String cardNo) {
        AssignBankcardRequest request = new AssignBankcardRequest();
        request.setCardNumber(cardNo);
        request.setBankcardId(142);
        request.setAutoActive(true);
        String result = postData(uId, MerchantCardMethods.UCARD_ASSIGN_CARD, request, null);
        System.out.println("assignCard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("assignCard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("assignCard encode result===>" + descStr);
        }
    }

    /**
     * activate card
     * {"cardId":"2309a2f3-0956-407e-9f38-18173cb5042f","cardNo":"4096360800070521"}
     * @param uId
     */
    public static void activateCard(String uId) {
        ActivateBankcardRequest request = new ActivateBankcardRequest();
        request.setActivationCode("75660168");
        request.setUserBankcardId(3366);

        String result = postData(uId, MerchantCardMethods.UCARD_ACTIVATE_CARD, request, null);
        System.out.println("activateCard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("activateCard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("activateCard encode result===>" + descStr);
        }
    }

    /**
     * util method
     * send post data
     *
     * @param uId
     * @param method
     * @param request
     * @return
     */
    private static String postData(String uId, String method, APApiBaseRequest request, String requestOrderId) {

        String jsonDataString = JSON.toJSONString(request);
        String url = GATEWAY + method;
        System.out.println("url=" + url);
        System.out.println("post path：" + method);
        System.out.println("body：" + jsonDataString);

        String sendContent = method + jsonDataString;
        System.out.println("originString=" + sendContent);
        String signature = APEncryptUtil.encode(APP_SECRET, sendContent);
        System.out.println("sign=" + signature);
        HttpRequest httpRequest = HttpRequest.post(url).header("appId", APP_ID).header("sign", signature);

        if (!Strings.isNullOrEmpty(uId)) {
            httpRequest.header("uId", uId);
        }

        if (!Strings.isNullOrEmpty(requestOrderId)) {
            httpRequest.header("requestOrderId", requestOrderId);
        }
        System.out.println("post all headers: " + httpRequest.headers());

        if (useProxy) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
            httpRequest.setProxy(proxy);
        }

        httpRequest.timeout(NOTIFY_TIMEOUT)
                .body(jsonDataString)
                .charset(StandardCharsets.UTF_8)
                .setConnectionTimeout(NOTIFY_CONNECT_TIMEOUT);
        return httpRequest.execute().body();
    }

    private static String postFormFile(String uId, String filedName, String filePath, String method) {

        String url = GATEWAY + method;
        System.out.println("url=" + url);
        System.out.println("post form file path：" + method);
        HttpRequest httpRequest = HttpRequest.post(url).form(filedName, new File(filePath)).header("appId", APP_ID);

        if (!Strings.isNullOrEmpty(uId)) {
            httpRequest.header("uId", uId);
        }

        System.out.println("post form file all headers: " + httpRequest.headers());

        if (useProxy) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
            httpRequest.setProxy(proxy);
        }

        httpRequest.timeout(NOTIFY_TIMEOUT)
                .charset(StandardCharsets.UTF_8)
                .setConnectionTimeout(NOTIFY_CONNECT_TIMEOUT);
        return httpRequest.execute().body();
    }
}
