package in.test.backend.dtos.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateConfigAttributeResponse {

    private int id;
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("attribute_name")
    private String attributeName;
    
    @JsonProperty("attribute_value")
    private String attributeValue;
    
    @JsonProperty("created_at")
    private String createdAt;
    
    @JsonProperty("updated_at")
    private String updatedAt;

}
