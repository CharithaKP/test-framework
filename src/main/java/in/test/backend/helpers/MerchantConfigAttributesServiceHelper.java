package in.test.backend.helpers;

import in.test.backend.base.RequestType;
import in.test.backend.base.RestClient;
import in.test.backend.common.ServiceEndpoints;
import in.test.backend.common.ServiceHelper;
import in.test.backend.dtos.requestDto.SampleRequest;
import in.test.backend.dtos.responseDto.SampleResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class MerchantConfigAttributesServiceHelper implements ServiceHelper {
    
    private final RestClient restClient;
    private final String baseUrl;
    private SampleRequest request;
    private SampleResponse response;
    private int expectedStatusCode = 200;
    
    public MerchantConfigAttributesServiceHelper(String baseUrl) {
        this.restClient = new RestClient();
        this.baseUrl = baseUrl;
    }
    
    @Override
    public ServiceHelper init() {
        log.info("Initializing MerchantConfigAttributesServiceHelper with base URL: {}", baseUrl);
        return this;
    }
    
    @Override
    public ServiceHelper process() {
        log.info("Processing merchant config attributes request for merchant: {}", request.getAttribute1());
        
        RequestSpecification requestSpec = given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(request);
        
        response = restClient.getResponse(
                RequestType.POST,
                requestSpec.basePath(ServiceEndpoints.SAMPLE_ENDPOINT.getUri()),
                SampleResponse.class,
                expectedStatusCode
        );
        
        return this;
    }
    
    @Override
    public ServiceHelper validate() {
        log.info("Validating merchant config attributes response");
       // validator.validateResponse(response, request);
        return this;
    }
    
    @Override
    public ServiceHelper transform() {
        log.info("Transforming merchant config attributes data");
        // Any data transformation logic can be added here
        return this;
    }
    
    @Override
    public ServiceHelper test() {
        log.info("Testing merchant config attributes functionality");
        return this.init().process().validate().transform();
    }

}
