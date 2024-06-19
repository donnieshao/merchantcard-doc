package com.merchantcard.api;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.merchantcard.constants.MerchantCardMethods;
import com.merchantcard.models.ApiResponse;
import com.merchantcard.models.APApiBaseRequest;
import com.merchantcard.models.*;
import com.merchantcard.models.SystemClockRequest;
import com.merchantcard.utils.Base64ImgUtil;
import com.merchantcard.utils.APEncryptUtil;
import com.google.common.base.Strings;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;

public class MerchantCardApi {




    // test env gateway
    private static final String GATEWAY =  "https://test.moonbank.me/api-web";
    // APPID
    private static final String APP_ID = "app_447770";
    // SECRET
    private static String APP_SECRET = "b635dd5c87f7bf73387929203321b1e1";


    private static final int NOTIFY_TIMEOUT = 15000;

    private static final int NOTIFY_CONNECT_TIMEOUT = 1000;

    // if use proxy ,set this value true
    private static boolean useProxy = false;

    // proxy ip
    private static String proxyAddress = "127.0.0.1";

    // proxy port
    private static int proxyPort = 7070;


    /**
     * get system clock(system status)
     */
    public static void getSystemClock() {

        SystemClockRequest request = new SystemClockRequest();
        String result = postData(null, MerchantCardMethods.SYS_CLOCK, request,null);
        System.out.println("getSystemClock response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("getSystemClock response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("getSystemClock encode===>" + descStr);
        }
    }

    /**
     * get template list
     */
    public static void bankcardTemplateList() {
        BankcardTemplateListRequest request = new BankcardTemplateListRequest();
        String result = postData(null, MerchantCardMethods.BANKCARD_TEMPLATE_LIST, request,null);
        System.out.println("bankcardTemplateList response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("bankcardTemplateList response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("bankcardTemplateList encode result===>" + descStr);
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
        String result = postData(null, MerchantCardMethods.USER_REGISTER, request,null);
        System.out.println("userRegister response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("userRegister response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("userRegister encode result===>" + descStr);
        }
    }

    public static void setUserInfo(String uId) {
        SetUserInfoRequest request = new SetUserInfoRequest();
        request.setFirstName("东营");
        request.setLastName("邵");
        request.setFirstNameEnglish("Dongying");
        request.setLastNameEnglish("Shao");
        request.setNationality("CN");
        request.setDateOfBirth("1987-04-04");
        UserInfoAddressVo addressVo = new UserInfoAddressVo();
        addressVo.setAddressLine1("Chaoyang ,baiziwan");
        addressVo.setCity("Beijing");
        addressVo.setCountryCode("CN");
        request.setAddress(addressVo);
        IdentificationVo identificationVo = new IdentificationVo();
        identificationVo.setIdentificationNumber("E67669656");
        identificationVo.setIdentificationType("PASSPORT");
        identificationVo.setIdentificationExpiryDate("2026-02-14");
        request.setIdentification(identificationVo);
        String result = postData(uId, MerchantCardMethods.SET_USER_INFO, request,null);
        System.out.println("setUserInfo response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("setUserInfo response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("setUserInfo encode result===>" + descStr);
        }
    }

    /**
     * set user profession and user info
     *
     * @param uId
     */
    public static void setUserProfession(String uId) {
        UserSetProfessionRequest request = new UserSetProfessionRequest();
        request.setFirst_name("ming");
        request.setFirst_name_en("ming");
        request.setLast_name("li");
        request.setLast_name_en("li");
        request.setBirthday("2000-01-01");
        request.setId_type("passport");
        request.setCountry("CN");
        request.setNumber("123456");
        request.setExpiry_date("2027-01-01");
        // TODO 路径按需修改
        request.setFrontImg(Base64ImgUtil.GetImageStr("/Users/donnie/asinx-official-api-docs/src/main/resources/passport1.jpg", "jpg"));
        request.setBackImg(Base64ImgUtil.GetImageStr("/Users/donnie/asinx-official-api-docs/src/main/resources/passport2.jpg", "jpg"));
        String result = postData(uId, MerchantCardMethods.SET_USER_PROFESSION, request,null);
        System.out.println("setUserProfession response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("setUserProfession response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("setUserProfession encode result===>" + descStr);
        }
    }

    public static void setHolderInfo(String uId) {
        SetCardHolderRequest request = new SetCardHolderRequest();
        request.setFirstName("ming");
        request.setLastName("ming");
        request.setPhoneNumber("123456");
        request.setCountryCode("CN");
        request.setResidentialAddressCity("BJ");
        request.setResidentialAddressCountry("China");
        request.setResidentialAddressLine1("line1");
        request.setResidentialAddressLine2("line2");
        request.setResidentialAddressState("BJ");
        request.setResidentialAddressPostalCode("10010");
        String result = postData(uId, MerchantCardMethods.SET_HOLDER_INFO, request,null);
        System.out.println("setHolderInfo response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("setHolderInfo response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("setHolderInfo encode result===>" + descStr);
        }
    }

    /**
     * apply bankcard
     *
     * @param uId
     * @param bankcardId
     * @param residenceAddress
     */
    public static void applyBankcard(String uId, Integer bankcardId, Integer userBankcardId, String residenceAddress) {
        ApplyBankcardRequest request = new ApplyBankcardRequest();
        request.setBankcardId(bankcardId);
//        request.setUserBankcardId(userBankcardId);
        request.setResidenceAddress(residenceAddress);
//        request.setTag("111111liwheefowhfoij");
        String result = postData(uId, MerchantCardMethods.APPLY_BANKCARD, request,null);
        System.out.println("applyBankcard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("applyBankcard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("applyBankcard encode result===>" + descStr);
        }
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
        String result = postData(uId, MerchantCardMethods.RECHARGE_BANKCARD, request,"ohweofhwoefhowehfouhwouh");
        System.out.println("rechargeBankcard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("rechargeBankcard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("success rechargeBankcard encode result===>" + descStr);
        }else{
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("failed rechargeBankcard encode result===>" + descStr);
        }
    }

    /**
     * set bankcard pin
     *
     * @param uId
     * @param userBankcardId
     * @param pin
     */
    public static void setBankcardPin(String uId, Integer userBankcardId, String pin) {
        SetBankcardPinRequest request = new SetBankcardPinRequest();
        request.setUserBankcardId(userBankcardId);
        request.setPin(pin);
        String result = postData(uId, MerchantCardMethods.SET_BANKCARD_PIN, request,null);
        System.out.println("setBankcardPin response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("setBankcardPin response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("setBankcardPin encode result===>" + descStr);
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
//        request.setFromTimestamp(1690878577000L);
//        request.setEndTimestamp(1690878578000L);
        request.setPageSize(100);
        request.setPageNum(1);
        String result = postData(uId, MerchantCardMethods.QUERY_BANKCARD_TRANSACTIONS, request,null);
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
        String result = postData(uId, MerchantCardMethods.QUERY_BANKCARD_BALANCE, request,null);
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
        String result = postData(uId, MerchantCardMethods.QUERY_BANKCARD_INFO, request,null);
        System.out.println("queryBankcardInfo response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("queryBankcardInfo response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("queryBankcardInfo encode result===>" + descStr);
        }
    }

    public static void queryBankcardOrder(String uId, Integer userBankcardId,String requestOrderId) {
        QueryBankcardOrderRequest request = new QueryBankcardOrderRequest();
        request.setUserBankcardId(userBankcardId);
        request.setRequestOrderId(requestOrderId);
        String result = postData(uId, MerchantCardMethods.CARD_ORDER, request,null);
        System.out.println("queryBankcardOrder response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("queryBankcardOrder response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("queryBankcardOrder encode result===>" + descStr);
        }
    }

    /**
     * uer recharge info
     *
     * @param uId
     */
    public static void userUSDRechargeInfo(String uId, BigDecimal amount) {
        UserRechargeInfoRequest request = new UserRechargeInfoRequest();
        request.setAmount(amount);
        String result = postData(uId, MerchantCardMethods.USD_RECHARGE_INFO, request,null);
        System.out.println("userRechargeInfo response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("userRechargeInfo response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("userRechargeInfo encode result===>" + descStr);
        }
    }

    /**
     *
     */
    @Deprecated
    public static void accountAsset() {
        QueryMerchantAssetRequest request = new QueryMerchantAssetRequest();
        String result = postData(null, MerchantCardMethods.USER_ACCOUNT_ASSET, request,null);
        System.out.println("AccountAsset response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("AccountAsset response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("AccountAsset encode result===>" + descStr);
        }
    }

    public static void accountRecharge() {
        QueryAccountRechargeRequest request = new QueryAccountRechargeRequest();
        request.setPageSize(10);
//        request.setUid("ewaoaylm5ueywbib");
//        request.setSymbol("USDT");
        request.setPageNum(1);
        String result = postData(null, MerchantCardMethods.USER_ACCOUNT_USER_RECHARGE, request,null);
        System.out.println("accountRecharge response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("merchantAsset response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("accountRecharge encode result===>" + descStr);
        }
    }

    public static void merchantAsset() {
        QueryMerchantAssetRequest request = new QueryMerchantAssetRequest();
        String result = postData(null, MerchantCardMethods.MERCHANT_ASSET, request,null);
        System.out.println("merchantAsset response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("merchantAsset response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("accountRecharge encode result===>" + descStr);
        }
    }

    public static void merchantRecharge() {
        QueryMerchantRechargeRequest request = new QueryMerchantRechargeRequest();
        request.setPageSize(10);
        request.setPageNum(1);
        String result = postData(null, MerchantCardMethods.MERCHANT_RECHARGE, request,null);
        System.out.println("merchantRecharge response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("merchantRecharge response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("merchantRecharge encode result===>" + descStr);
        }
    }

    public static void updateBankcardStatus(String uId, Integer userBankcardId,boolean enable) {
        UpdateBankcardStatusRequest request = new UpdateBankcardStatusRequest();
        request.setEnable(enable);
        request.setUserBankcardId(userBankcardId);

        String result = postData(uId, MerchantCardMethods.UPDATE_CARD_STATUS, request,null);
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

        String result = postData(uId, MerchantCardMethods.CLOSE_CARD, request,null);
        System.out.println("closeBankcard response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("closeBankcard response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("closeBankcard encode result===>" + descStr);
        }
    }

    @Deprecated
    public static void  kycGateway() {
        KycGatewayRequest request = new KycGatewayRequest();
        request.setDoneViewURL("https://www.asinx.io/done");
        request.setTimeoutViewURL("https://www.asinx.io/timeout");
        String result = postData(null, MerchantCardMethods.KYC_GATEWAY, request,null);
        System.out.println("kycGateway response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("kycGateway response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("kycGateway encode result===>" + descStr);
        }
    }

    public static void  kycCheck(String uId) {
        KycCheckRequest request = new KycCheckRequest();
        request.setIdType("PASSPORT");
        request.setCountry("CN");
        String result = postData(uId, MerchantCardMethods.KYC_CHECK, request,null);
        System.out.println("kycCheck response String:  " + result);
        ApiResponse<String> apiResponse = JSON.parseObject(result, new TypeReference<ApiResponse<String>>() {
        });
        System.out.println("kycCheck response Object:  " + apiResponse);
        if (apiResponse.isSuccess()) {
            String descStr = APEncryptUtil.decode(APP_SECRET, apiResponse.getResult());
            System.out.println("kycCheck encode result===>" + descStr);
        }
    }

    public static void main(String[] args) {
        getSystemClock();

//        bankcardTemplateList();
//        updateBankcardStatus();
//        setHolderInfo("35920");
//        kycGateway();

//        userRegister("86","110120","api_demo_test@asinx.io");
//        setUserProfession("35920");
//        applyBankcard("36061",24,null,"KR");
//        rechargeBankcard("36061",19306,new BigDecimal(8),new BigDecimal(50));
//        closeBankcard("36061",19306);
//        queryBankcardOrder("36046",19280,"CLOSE2406031639263553919");
//        updateBankcardStatus("36059",19300,true);
//        setUserInfo("26343");
//        kycCheck("26343");


//        setBankcardPin("35910",136,"123456");
//        queryBankcardTransactions("35974",19163);
//        queryBankcardBalance("35987",19250);
//        queryBankcardInfo("36046",19280);
//        userUSDRechargeInfo("35910",new BigDecimal(2));
//        accountAsset();
//        accountRecharge();
//        merchantAsset();
//        merchantRecharge();
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
    private static String postData(String uId, String method, APApiBaseRequest request,String requestOrderId) {

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

        if(!Strings.isNullOrEmpty(requestOrderId)){
            httpRequest.header("requestOrderId",requestOrderId);
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
}