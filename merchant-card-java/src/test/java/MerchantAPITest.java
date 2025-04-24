import com.merchantcard.api.MerchantCardApi;
import org.junit.jupiter.api.Test;

public class MerchantAPITest {

    /**
     * Query merchant asset information.
     *
     */
    @Test
    public void testMerchantAsset() {
        MerchantCardApi.merchantAsset();
    }

    /**
     * Query merchant recharge address information.
     */
    @Test
    public void testMerchantRechargeInfo() {
        MerchantCardApi.merchantRechargeInfo();
    }

    /**
     * Query merchant recharge records.
     */
    @Test
    public void testMerchantRechargeRecords() {
        MerchantCardApi.merchantRechargeRecords();
    }
}
