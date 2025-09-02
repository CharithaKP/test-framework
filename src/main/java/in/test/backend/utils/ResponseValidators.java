package in.test.backend.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseValidators implements IValidator {
    
    @Override
    public void validateNode() {
        log.info("Validating response node");
        // Node validation logic can be added here
    }
    
    @Override
    public void validateDBEntities() {
        log.info("Validating database entities");
        // Database validation logic can be added here
    }

}
