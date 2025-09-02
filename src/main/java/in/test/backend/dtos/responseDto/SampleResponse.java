package in.test.backend.dtos.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleResponse {

    @JsonProperty("attribute1")
    private String attribute1;
    
    @JsonProperty("attributes")
    private Map<String, String> attributes;

}
