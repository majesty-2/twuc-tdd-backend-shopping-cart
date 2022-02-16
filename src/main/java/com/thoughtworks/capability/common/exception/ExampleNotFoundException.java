package com.thoughtworks.capability.common.exception;


public class ExampleNotFoundException extends BaseException {

    public ExampleNotFoundException(Long id) {
        super("Example not found with id=" + id);
    }
}
