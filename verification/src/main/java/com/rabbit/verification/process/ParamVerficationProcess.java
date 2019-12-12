package com.rabbit.verification.process;

import com.rabbit.verification.annotation.ParamCheck;
import com.rabbit.verification.entity.BeanEntity;
import com.rabbit.verification.exception.VerificationException;
import com.rabbit.verification.model.BaseModel;
import com.rabbit.verification.util.PropertUtil;
import com.rabbit.verification.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author chentao
 * Date 2019/12/12
 * Description
 **/
public class ParamVerficationProcess {

    private static Map<Class<?>, Map<String, ParamCheck>> verficationMap = new ConcurrentHashMap<>();

    private static Map<String, ParamCheck> getVerificationInfo(Class<?> clazz){
        if(verficationMap.containsKey(clazz)){
            return verficationMap.get(clazz);
        }
        List<BeanEntity> entities = PropertUtil.getBeanFields(clazz);
        Map<String, ParamCheck> checkMap = new HashMap<>();
        for(BeanEntity entity : entities){
            ParamCheck check = entity.getSourceField().getAnnotation(ParamCheck.class);
            if(null == check){
                continue;
            }
            checkMap.put(entity.getFieldName(), check);
            if(BaseModel.class.isAssignableFrom(entity.getFieldType())){
                Map<String, ParamCheck> childCheckMap = getVerificationInfo(entity.getFieldType());
                if(!StringUtil.isNullOrEmpty(childCheckMap)){
                    for(String key : childCheckMap.keySet()){
                        ParamCheck paramCheck = childCheckMap.get(key);
                        if(!StringUtil.isNullOrEmpty(paramCheck)){
                            String[] orNulls = paramCheck.orNulls();
                            for(int i = 0; i < orNulls.length; i++){
                                orNulls[i] = entity.getFieldName() + "."  + orNulls[i];
                            }
                            try{
                                PropertUtil.setAnnotationValue(paramCheck, "orNulls", orNulls);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        checkMap.put(entity.getFieldName() + "." + key, childCheckMap.get(key));
                    }
                }
                continue;
            }
        }
        verficationMap.put(clazz, checkMap);
        return checkMap;
    }


    public static void checkPara(BaseModel model) {
        if (model == null) {
            return;
        }
        Map<String, ParamCheck> checkInfo = getVerificationInfo(model.getClass());
        for (String fieldName : checkInfo.keySet()) {
            ParamCheck check = checkInfo.get(fieldName);
            Object obj = PropertUtil.getFieldValue(model, fieldName);
            String error = check.errorMsg();
            // 数据可空验证
            if (!check.allowNull()) {
                if (StringUtil.isNullOrEmpty(obj)) {
                    throw new VerificationException((StringUtil.isNullOrEmpty(error)?"参数不能为空:":error)+fieldName);
                }
            }
            if (StringUtil.isNullOrEmpty(obj)) {
                if (!StringUtil.isNullOrEmpty(check.orNulls())) {
                    String[] orNulls=check.orNulls();
                    String currentNode=getCurrentNode(fieldName);
                    if(!StringUtil.isNullOrEmpty(currentNode)){
                        currentNode+=".";
                    }
                    for(int i=0; i< orNulls.length;i++){
                        orNulls[i]=currentNode+orNulls[i];
                    }
                    List<Object> values = PropertUtil.getFieldValues(model, orNulls);
                    if (StringUtil.isAllNull(values)) {
                        throw new VerificationException((StringUtil.isNullOrEmpty(error)?"参数不能同时为空:":error)+fieldName + "," + StringUtil.collectionMosaic(check.orNulls(), ","));

                    }
                }
                continue;
            }
            if (StringUtil.isNullOrEmpty(check.format())) {
                continue;
            }
            // 数据格式验证
            String currMatcher = null;
            for (String matcher : check.format()) {
                if (StringUtil.isMatcher(obj.toString(), matcher)) {
                    currMatcher = null;
                    break;
                }
                currMatcher = matcher;
            }
            if (!StringUtil.isNullOrEmpty(currMatcher)) {
                throw new VerificationException((StringUtil.isNullOrEmpty(error)?"参数不满足格式:":error)+fieldName+";format:"+currMatcher);
            }
        }
        return;
    }

    private static String getCurrentNode(String fieldName){
        if(!fieldName.contains(".")){
            return "";
        }
        return fieldName.substring(0, fieldName.lastIndexOf("."));
    }

    public static void checkPara(ParamCheck check, String fieldName, Object fieldValue,
                                 Map<String, Object> allParas) {
        if (StringUtil.isNullOrEmpty(check)) {
            return;
        }
        String error = check.errorMsg();
        // 数据可空验证
        if (!check.allowNull()) {
            if (StringUtil.isNullOrEmpty(fieldValue)) {
                throw new VerificationException((StringUtil.isNullOrEmpty(error)?"参数不能为空:":error)+fieldName);
            }
            if (!StringUtil.isNullOrEmpty(check.orNulls())) {
                List<Object> values = PropertUtil.getFieldValues(allParas, check.orNulls());
                if (!StringUtil.isAllNull(values)) {
                    throw new VerificationException((StringUtil.isNullOrEmpty(error)?"参数不能同时为空:":error)+fieldName + "," + StringUtil.collectionMosaic(check.orNulls(), ","));
                }
            }
        }
        // 数据格式验证
        if (StringUtil.isNullOrEmpty(fieldValue)) {
            return;
        }
        if (StringUtil.isNullOrEmpty(check.format())) {
            return;
        }
        // 数据格式验证
        String currMatcher = null;
        for (String matcher : check.format()) {
            if (StringUtil.isMatcher(fieldValue.toString(), matcher)) {
                currMatcher = null;
                break;
            }
            currMatcher = matcher;
        }
        if (!StringUtil.isNullOrEmpty(currMatcher)) {
            throw new VerificationException((StringUtil.isNullOrEmpty(error)?"参数不满足格式:":error)+fieldName+";format:"+currMatcher);
        }
        return;
    }

}

