package in.test.backend.common;

import lombok.Getter;

@Getter
public enum ServiceEndpoints {

    TEMPLATE_ENDPOINT("heimdall/v1/merchant/config/attributes");

    private final String uri;
    
    ServiceEndpoints(String uri) {
        this.uri = uri;
    }
    
    public String getUri() {
        return uri;
    }
}
