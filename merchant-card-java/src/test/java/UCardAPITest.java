import com.merchantcard.api.MerchantCardApi;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class UCardAPITest {

    @Test
    /**
     * Retrieve the product information of the current merchant.
     */
    public void testBankcardTemplateList() {
        MerchantCardApi.bankcardTemplateList();
    }

    /**
     * Register a new user
     * The returned user ID (UID) will be used for subsequent method calls.
     */
    @Test
    public void register() {
        MerchantCardApi.userRegister("+49","15150819","test@outlook.com");
    }

    /**
     * Upload KYC file, single image
     *
     * The returned file ID will be used for subsequent KYC submission methods
     */
    @Test
    public void testUploadFile() {
        MerchantCardApi.ucardUploadFile("38071");//067c5f62-9b28-4854-8062-c990f11e98b9
    }

    /**
     * Submit UCard KYC
     *
     * The submitted KYC information will be reviewed by auditors
     */
    @Test
    public void testuCardKYCApply(){
        MerchantCardApi.uCardKYCApply("38173");
    }

    /**
     * Check the status of Ucard KYC audit.
     */
    @Test
    public void testQueryUCardKYCStatus(){
        MerchantCardApi.uCardKycStatus("38173");
    }

    /**
     * Assign cards to users.
     *
     * If autoActive=true, the card will be automatically activated after allocation.
     */
    @Test
    public void testAssignCard() {
        MerchantCardApi.assignCard("38071","4096360800079258",true);
    }

    /**
     * Query card balance
     */
    @Test
    public void testQueryBankcardBalance() {
        MerchantCardApi.queryBankcardBalance("81795", 33293);
    }

    /**
     * Query card details
     */
    @Test
    public void testCardInfo(){
        MerchantCardApi.queryBankcardInfo("38134",4121);
    }


    /**
     * Recharge the card
     *
     *  1.Suggest using the target amount parameter, which is the amount of increase in card balance.
     *  2.It is recommended to use the requestOrderId header to automatically generate the order number.
     *    You can use the order query method to check the recharge processing results.
     *  3.If the code returned by the recharge method is 99997,
     *  then the processing result cannot be understood and needs to be checked and confirmed by the webhook or using the order query method.
     */
    @Test
    public void testRecharge(){

        String requestOrderId = UUID.randomUUID().toString();
        //37525
        MerchantCardApi.rechargeBankcard("90166", 39391, null, new BigDecimal(21),requestOrderId);
    }

    /**
     * Query the processing results of business (recharge, card cancellation).
     *
     */
    @Test
    public void testOrderQuery() {
        MerchantCardApi.queryBankcardOrder("38017", 3963, "d195386c-7510-46a7-a9ef-eb989be01835-0");
    }

    /**
     * Update the card status, freeze or unfreeze the card.
     *
     * ture=unfreeze
     * false=freeze
     */
    @Test
    public void testUpdateCardStatus(){
        MerchantCardApi.updateBankcardStatus("38568",4426,false);
    }

    /**
     * Query card transaction records.
     *
     * The recharge of the card is not included in this record, only the user's card swiping consumption record.
     */
    @Test
    public void queryBankcardTransactions() {
        MerchantCardApi.queryBankcardTransactions("37525",3734);
    }


}
