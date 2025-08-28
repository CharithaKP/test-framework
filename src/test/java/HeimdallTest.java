import in.test.backend.dtos.requestDto.UpdateConfigAttributeRequest;
import in.test.backend.dtos.responseDto.UpdateConfigAttributeResponse;
import in.test.backend.utils.RequestBuilders;
import org.testng.annotations.Test;

import static in.test.backend.clients.HeimdallClient.updateConfigAttributeRequest;


public class HeimdallTest {


    @Test(description = "")
    public static void test1(){
        UpdateConfigAttributeRequest request = RequestBuilders.updateConfigAttributeRequestBuilder("scar_transaction_enabled",true);
        UpdateConfigAttributeResponse response = updateConfigAttributeRequest(request);


    }

}
