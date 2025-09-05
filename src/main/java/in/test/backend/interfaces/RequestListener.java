package in.test.backend.interfaces;

import in.test.backend.base.RequestType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Interface for listening to request lifecycle events
 */
public interface RequestListener {
    
    /**
     * Called before a request is executed
     * @param requestType The HTTP method being used
     * @param requestSpec The request specification
     */
    default void onBeforeRequest(RequestType requestType, RequestSpecification requestSpec) {
        // Default implementation does nothing
    }
    
    /**
     * Called after a successful request
     * @param requestType The HTTP method that was used
     * @param requestSpec The request specification that was used
     * @param response The response received
     * @param attemptNumber The attempt number (1 for first attempt, 2 for first retry, etc.)
     */
    default void onRequestSuccess(RequestType requestType, RequestSpecification requestSpec, Response response, int attemptNumber) {
        // Default implementation does nothing
    }
    
    /**
     * Called after a failed request (before retry if retries are configured)
     * @param requestType The HTTP method that was used
     * @param requestSpec The request specification that was used
     * @param exception The exception that occurred
     * @param attemptNumber The attempt number (1 for first attempt, 2 for first retry, etc.)
     */
    default void onRequestFailure(RequestType requestType, RequestSpecification requestSpec, Exception exception, int attemptNumber) {
        // Default implementation does nothing
    }
    
    /**
     * Called before a retry attempt
     * @param requestType The HTTP method being retried
     * @param requestSpec The request specification
     * @param previousException The exception from the previous attempt
     * @param retryAttempt The retry attempt number (1 for first retry, 2 for second retry, etc.)
     * @param maxRetries The maximum number of retries configured
     */
    default void onBeforeRetry(RequestType requestType, RequestSpecification requestSpec, Exception previousException, int retryAttempt, int maxRetries) {
        // Default implementation does nothing
    }
    
    /**
     * Called when all retry attempts have been exhausted
     * @param requestType The HTTP method that failed
     * @param requestSpec The request specification that was used
     * @param finalException The final exception
     * @param totalAttempts The total number of attempts made
     */
    default void onRetryExhausted(RequestType requestType, RequestSpecification requestSpec, Exception finalException, int totalAttempts) {
        // Default implementation does nothing
    }
}
