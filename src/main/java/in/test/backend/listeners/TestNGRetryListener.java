package in.test.backend.listeners;

import in.test.backend.annotations.Retry;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TestNG Retry Analyzer that integrates with XML configuration and suite parameters
 * Supports both annotation-based and XML-based retry configuration
 * 
 * Features:
 * - Reads configuration from TestNG suite parameters
 * - Supports XML-based retry configuration
 * - Method and class-level retry annotations
 * - Global retry statistics tracking
 * - Configurable retry delays
 */
@Slf4j
public class TestNGRetryListener implements IRetryAnalyzer, IAnnotationTransformer, ITestListener {
    
    // Global retry statistics
    private static final AtomicInteger TOTAL_RETRIES = new AtomicInteger(0);
    private static final AtomicInteger TOTAL_FAILED_TESTS = new AtomicInteger(0);
    private static final AtomicInteger TOTAL_SUCCESSFUL_RETRIES = new AtomicInteger(0);
    private final AtomicInteger retries = new AtomicInteger(1);




    // Per-test retry tracking
    private final ConcurrentHashMap<String, AtomicInteger> testRetryCount = new ConcurrentHashMap<>();
    
    // Configuration
    private static int MAX_RETRIES_FOR_TEST = 3;
    private static int RETRY_DELAY_IN_SECONDS = 1;
    private static boolean RETRY_ENABLED = true;

    
    /**
     * Get total number of retries performed across all tests
     */
    public static int getTotalRetries() {
        return TOTAL_RETRIES.get();
    }
    
    /**
     * Get total number of failed tests
     */
    public static int getTotalFailedTests() {
        return TOTAL_FAILED_TESTS.get();
    }
    
    /**
     * Get total number of successful retries
     */
    public static int getTotalSuccessfulRetries() {
        return TOTAL_SUCCESSFUL_RETRIES.get();
    }
    
    /**
     * Get retry success rate as percentage
     */
    public static double getRetrySuccessRate() {
        int totalRetries = TOTAL_RETRIES.get();
        if (totalRetries == 0) {
            return 0.0;
        }
        return (double) TOTAL_SUCCESSFUL_RETRIES.get() / totalRetries * 100.0;
    }
    
    /**
     * Print retry statistics summary
     */
    public static void printRetryStatistics() {
        log.info("=== TestNG Retry Statistics ===");
        log.info("Total Retries: {}", getTotalRetries());
        log.info("Total Failed Tests: {}", getTotalFailedTests());
        log.info("Total Successful Retries: {}", getTotalSuccessfulRetries());
        log.info("Retry Success Rate: {:.2f}%", getRetrySuccessRate());
        log.info("===============================");
    }
    
    @Override
    public void onStart(final ITestContext context) {
        final String MAX_RETRIES_DEFINED_IN_SUITE = context.getCurrentXmlTest().getParameter("maxRetriesFromSuite");
        final String DELAY_BETWEEN_RETRY_IN_SECONDS_IN_SUITE = context.getCurrentXmlTest().getParameter("delayBetweenRetryInSeconds");
        MAX_RETRIES_FOR_TEST = RetryProperties.getMaxRetries(MAX_RETRIES_DEFINED_IN_SUITE);
        RETRY_DELAY_IN_SECONDS = RetryProperties.getDelayInSeconds(DELAY_BETWEEN_RETRY_IN_SECONDS_IN_SUITE);
        log.info(String.format("Max retries user has set to : %s", MAX_RETRIES_FOR_TEST));
        log.info(String.format("Delay between retries is set to : %s", RETRY_DELAY_IN_SECONDS));
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        final Retry retry = testMethod.getDeclaringClass().getAnnotation(Retry.class);

        if (retry != null && !retry.enable()) {
            log.info("user choose to skip retries...");
        } else {
            annotation.setRetryAnalyzer(this.getClass());
        }
    }

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (!iTestResult.isSuccess()) {
            int count = retries.getAndIncrement();
            if (count <= MAX_RETRIES_FOR_TEST) {
                TOTAL_RETRIES.getAndIncrement();
                waitBeforeRetry();
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    private void waitBeforeRetry() {
        try {
            Thread.sleep((long) RETRY_DELAY_IN_SECONDS * 1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
    
    @Override
    public void onFinish(ITestContext context) {
        log.info("=== TestNG Retry Analyzer Finished ===");
        printRetryStatistics();
        
        // Clear test-specific retry counters for next run
        testRetryCount.clear();
    }

}
