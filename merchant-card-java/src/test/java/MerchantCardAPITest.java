import com.merchantcard.api.MerchantCardApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        MerchantCardApi.userRegister("82","198811201","4661test@tester.com");
    }
    @Test
    public void queryBankcardTransactions() {
        MerchantCardApi.queryBankcardTransactions("68308",27667);
    }




}
