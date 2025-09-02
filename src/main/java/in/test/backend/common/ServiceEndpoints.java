package in.test.backend.common;

import lombok.Getter;

@Getter
public enum ServiceEndpoints {


    SAMPLE_ENDPOINT("");

    private final String uri;
    
    ServiceEndpoints(String uri) {
        this.uri = uri;
    }
    
    public String getUri() {
        return uri;
    }
}
