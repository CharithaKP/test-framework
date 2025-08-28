package in.test.backend.utils;

import in.test.backend.dtos.requestDto.UpdateConfigAttributeRequest;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilders {

    public static UpdateConfigAttributeRequest updateConfigAttributeRequestBuilder(String AttributeKey, Boolean AttributeValue){
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put(AttributeKey, AttributeValue);
        UpdateConfigAttributeRequest updateConfigAttributeRequest = new UpdateConfigAttributeRequest();
        updateConfigAttributeRequest.setMerchantId("7731bb75-b793-49c9-836d-ce8629a79978");
        updateConfigAttributeRequest.setAttributes(attributesMap);
        return updateConfigAttributeRequest;
    }

}
