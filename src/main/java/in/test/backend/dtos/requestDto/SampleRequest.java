package in.test.backend.dtos.requestDto;

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
public class SampleRequest {
    
    @JsonProperty("attribute1")
    private String attribute1;
    
    @JsonProperty("attribute2")
    private Map<String, String> attribute2;

}
