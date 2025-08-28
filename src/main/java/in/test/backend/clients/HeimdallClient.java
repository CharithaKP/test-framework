package in.test.backend.clients;

import in.test.backend.base.RequestType;
import in.test.backend.base.RestClient;
import in.test.backend.dtos.requestDto.UpdateConfigAttributeRequest;
import in.test.backend.dtos.responseDto.UpdateConfigAttributeResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static in.test.backend.base.ServiceURIs.HEIMDALL_URL;
import static in.test.backend.common.ServiceEndpoints.TEMPLATE_ENDPOINT;

public class HeimdallClient {
    protected static final RestClient restClient = new RestClient();

    public static UpdateConfigAttributeResponse updateConfigAttributeRequest(UpdateConfigAttributeRequest updateConfigAttributeRequest){
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeaders(headers)
                .setBody(updateConfigAttributeRequest)
                .setBasePath(TEMPLATE_ENDPOINT.getUri())
                .setBaseUri(HEIMDALL_URL.getService()).build();
        return restClient.getResponse(RequestType.POST, requestSpecification,UpdateConfigAttributeResponse[].class, 200)[0];

    }

}
