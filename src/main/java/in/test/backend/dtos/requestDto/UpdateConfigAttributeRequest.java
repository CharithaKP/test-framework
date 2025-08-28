package in.test.backend.dtos.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateConfigAttributeRequest {

    @JsonProperty("merchant_id")
    String merchantId;
    
    Map<String, Object> attributes;
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public Map<String, Object> getAttributes() {
        return attributes;
    }

}
