package com.crud_restfulapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID=1L; // ????

    private String resourceName;
    private String fieldName;
    private long fieldValueInt;
    private String fieldValueString;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValueInt) {
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValueInt));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueInt = fieldValueInt;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueString) {
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValueString));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueString = fieldValueString;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public long getFieldValueInt() {
        return fieldValueInt;
    }

    public void setFieldValueInt(long fieldValueInt) {
        this.fieldValueInt = fieldValueInt;
    }

    public String getFieldValueString() {
        return fieldValueString;
    }

    public void setFieldValueString(String fieldValueString) {
        this.fieldValueString = fieldValueString;
    }
}
