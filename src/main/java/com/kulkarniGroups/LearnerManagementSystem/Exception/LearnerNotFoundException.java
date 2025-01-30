package com.kulkarniGroups.LearnerManagementSystem.Exception;

public class LearnerNotFoundException extends Exception {
    //Runtime exception is unchecked exception, if you mentioned as Exception, you need to mention it.
    public LearnerNotFoundException(String message) {
        super(message);

    }
}
