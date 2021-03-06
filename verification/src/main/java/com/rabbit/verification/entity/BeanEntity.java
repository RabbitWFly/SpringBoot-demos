package com.rabbit.verification.entity;

import com.rabbit.verification.util.StringUtil;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Author chentao
 * Date 2019/12/12
 * Description
 **/
@SuppressWarnings("serial")
public class BeanEntity implements Serializable {

    private String fieldName;
    private Object fieldValue;
    private Class<?> fieldType;
    private Annotation[] fieldAnnotations;
    private Field sourceField;

    @SuppressWarnings("uncheck")
    public <T extends Annotation> T getAnnotation(Class<?> clazz){
        if(StringUtil.isNullOrEmpty(fieldAnnotations)){
            return null;
        }
        for(Annotation annotation : fieldAnnotations){
            if(clazz.isAssignableFrom(annotation.annotationType())){
                return (T) annotation;
            }
        }
        return null;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public Annotation[] getFieldAnnotations() {
        return fieldAnnotations;
    }

    public void setFieldAnnotations(Annotation[] fieldAnnotations) {
        this.fieldAnnotations = fieldAnnotations;
    }

    public Field getSourceField() {
        return sourceField;
    }

    public void setSourceField(Field sourceField) {
        this.sourceField = sourceField;
    }
}

