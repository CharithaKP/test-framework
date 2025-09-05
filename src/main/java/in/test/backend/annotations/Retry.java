package in.test.backend.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to control retry behavior for test methods
 * Can be applied at class level to affect all test methods in the class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Retry {
    
    /**
     * Whether to enable retries for this test/class
     * @return true to enable retries, false to disable
     */
    boolean enable() default true;
    
    /**
     * Maximum number of retries for this specific test/class
     * If not specified, uses the configuration from XML or suite parameters
     * @return maximum retry count
     */
    int maxRetries() default -1;
    
    /**
     * Delay between retries in seconds for this specific test/class
     * If not specified, uses the configuration from XML or suite parameters
     * @return delay in seconds
     */
    int delayInSeconds() default -1;
    
    /**
     * Name of the retry configuration to use from XML file
     * If specified, loads configuration from retry-config.xml
     * @return configuration name
     */
    String configName() default "";
}
