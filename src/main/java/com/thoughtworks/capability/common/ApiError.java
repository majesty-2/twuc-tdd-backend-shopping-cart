package com.thoughtworks.capability.common;

import lombok.Value;

@Value
public class ApiError {
    Integer status;
    String error;
    String message;
}
