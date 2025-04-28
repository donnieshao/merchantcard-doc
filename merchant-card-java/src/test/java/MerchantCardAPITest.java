import com.merchantcard.api.MerchantCardApi;
import com.merchantcard.models.ws.WsCardHolderRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

/***
 *
 *

 public static void main(String[] args) {
 //        uCardKYCApply("37635");
 //        uCardKycStatus("37169");
 //        assignCard("4096360800070539");
 //        activateCard("37635");
 //        ucardUploadFile("37090");
 //      setUserInfo("30622");
 //       kycCheck("30622");
 //       kycStatus("31069");
 //5        getPin(19290,"35968")
 //        query3dsAuth("CTX3DS1617206022504481","35920");
 //        approve3dsAuth("CTX3DS1617206022504481","35920");
 //        reject3dsAuth("CTX3DS1617206022504481","35920");
 //;
 //        usdToEur(BigDecimal.TEN,"35920");
 //        usdRecharge("1",new BigDecimal(10));
 //
 //        getSystemClock();
 //
 //        bankcardTemplateList();
 //        activeBankcard("59431",85,"5246042602003720");


 //        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
 //        for (int i = 0  ; i < 100; i++) {
 //            fixedThreadPool.execute(()->{
 //                try {
 //                    Integer integer = applyBankcard("80345", 104, null, null);
 //                    rechargeBankcard("80345", 32625, new BigDecimal(8), new BigDecimal(20));
 //                }catch (Exception e) {
 //                    e.printStackTrace();
 //                }
 //            });
 //        }


 //
 //        Date start = new Date();
 //        rechargeBankcard("36225",1315,new BigDecimal(8),new BigDecimal(50));
 //        rechargeBankcard("36214",695,new BigDecimal(8),new BigDecimal(50));
 //        rechargeBankcard("36214",696,new BigDecimal(8),new BigDecimal(50));
 //        rechargeBankcard("36214",697,new BigDecimal(8),new BigDecimal(50));
 //        rechargeBankcard("36214",698,new BigDecimal(8),new BigDecimal(50));

 //        Date end = new Date();
 //        System.out.println((end.getTime()-start.getTime()+ "ms"));
 //        merchantHistoryLogs();
 //        closeBankcard("2",3432);
 //                for (int i = 0; i < 1000; i++) {
 //        queryBankcardOrder("31974",20875,"CLOSE2412110416281285926");

 //        }
 //        updateBankcardStatus("55191",23631,true);
 //
 //
 //        queryBankcardTransactions("59431",24533);
 //        queryBankcardBalance("80345",32601);
 //        queryBankcardInfo("80345",32552);
 //        merchantAsset();
 //        merchantRechargeRecords();
 //        merchantRechargeInfo();
 //        assignCard("37169");
 //        activateCard("35920");
 //        holdersSetInfo("35920");
 //        setWHolderInfo("80345");
 }
 */
@Slf4j
public class MerchantCardAPITest {

    private long startTime;

    @BeforeEach
    void beforeTest() {
        startTime = System.nanoTime(); // 记录开始时间
    }

    @AfterEach
    void afterTest() {
        long elapsedTime = System.nanoTime() - startTime;
        log.info("Test execution time: " + (elapsedTime / 1_000_000) + " ms");
    }

    @Test
    public void test() {
        log.info("test");
    }

    @Test
    public void testGetSystemClock() {
        MerchantCardApi.getSystemClock();
    }

    @Test
    public void register() {
        // 37910
        MerchantCardApi.userRegister("+49","15150819731","dennisposchner@outlook.com");
    }

    @Test
    public void testSetWHolderInfo() {
        MerchantCardApi.setWHolderInfo("81795");
    }

    @Test
    public void testBankcardTemplateList() {
        MerchantCardApi.bankcardTemplateList();
    }

    @Test
    public void testApply() {
        MerchantCardApi.applyBankcard("90166", 180, null, null);
    }

    @Test
    public void testRecharge(){
        //37525
        MerchantCardApi.rechargeBankcard("90166", 39391, new BigDecimal(8), new BigDecimal(21), UUID.randomUUID().toString());
    }

    @Test
    public void testQueryBankcardBalance() {
        MerchantCardApi.queryBankcardBalance("37731", 4365);
    }

    @Test
    public void queryBankcardTransactions() {
        MerchantCardApi.queryBankcardTransactions("37731",4365);
    }

    @Test
    public void testUploadFile() {
        MerchantCardApi.ucardUploadFile("38071");//067c5f62-9b28-4854-8062-c990f11e98b9
    }

    @Test
    public void testuCardKYCApply(){
        MerchantCardApi.uCardKYCApply("38173");
    }

    @Test
    public void testAssignCard() {
        MerchantCardApi.assignCard("38071","4096360800079258",true);
    }

    @Test
    public void testWholderInfo() {
        MerchantCardApi.setWHolderInfo("37984");
    }

    @Test
    public void testKycapply() {
        MerchantCardApi.uCardKYCApply("37731");
    }

    @Test
    public void testOrderQuery() {
        MerchantCardApi.queryBankcardOrder("38017", 3963, "d195386c-7510-46a7-a9ef-eb989be01835-0");
    }

    @Test
    public void testCardInfo(){
        MerchantCardApi.queryBankcardInfo("38134",4121);
    }

    @Test
    public void testMerchantRechargeInfo(){
        MerchantCardApi.merchantRechargeInfo();
    }

    @Test
    public void testMerchantHistoryLog(){
        MerchantCardApi.merchantHistoryLogs();
    }

    @Test
    public void testRechargeRecords(){
        MerchantCardApi.merchantRechargeRecords();

    }

    @Test
    public void testStatus(){
        MerchantCardApi.updateBankcardStatus("38568",4426,false);
    }


    @Test
    public void testWsHolder(){
        WsCardHolderRequest request = new WsCardHolderRequest();
        request.setBankcardId(180)
                .setAreaCode("+49")
                .setMobile("15150819731")
                .setEmail("dennisposchner@outlook.com")
                .setFirstName("john")
                .setLastName("zhang")
                .setBirthday("1999-11-19")
                .setCountry("US")
                .setTown("US_QEE")
                .setAddress("1861  Henry Ford Avenue")
                .setPostCode("10007");
        MerchantCardApi.createWSHolder("90166",request);
    }

    @Test
    public void testUpdateWsHolder(){
        WsCardHolderRequest request = new WsCardHolderRequest();
        request.setHolderId(28442L)
                .setAreaCode("+1")
                .setMobile("9178595315")
                .setEmail("wulixroszn@gmail.com")
                .setFirstName("Mu")
                .setLastName("ha")
                .setBirthday("1983-11-19")
                .setCountry("US")
                .setTown("US_QEE")
                .setAddress("1149  Williams Avenue")
                .setPostCode("10001");
        MerchantCardApi.updateWSHolder("83501",request);
    }

    @Test
    public void testQueryWsHolder(){
        WsCardHolderRequest request = new WsCardHolderRequest();
        request.setHolderId(58052L);
        MerchantCardApi.queryWSHolder("38156",request);
    }
    public void testListRegion() {
        MerchantCardApi.wsListRegion("37731");
    }
    @Test
    public void testWsListCity() {
        MerchantCardApi.wsListCity("37731");
    }
    @Test
    public void testWsListMobileArea() {
        MerchantCardApi.wsListMobileArea("37731");
    }

    @Test
    public void testNoPinAmount(){
        MerchantCardApi.setNoPinAmount("38708",4023,"100");
    }
}
