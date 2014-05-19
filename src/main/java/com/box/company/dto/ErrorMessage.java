package com.box.company.dto;

/**
 * Created by Owner on 5/17/14.
 */
public class ErrorMessage {
    private String fieldName;
    private String errorCode;
    private String message;

    public ErrorMessage() { }

    public ErrorMessage(String fieldName, String errorCode, String message) {
        this.fieldName = fieldName;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
