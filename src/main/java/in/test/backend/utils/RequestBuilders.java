package in.test.backend.utils;

import in.test.backend.dtos.requestDto.SampleRequest;


public class RequestBuilders implements IRequestBuilder<SampleRequest> {

    public void SampleRequest() {

    }
        @Override
        public SampleRequest init() {
            return SampleRequest.builder().build();

    }

}
