package com.leo.springboogapi.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class CustomBeanUtils {
    /**
     * 獲取所有的屬性值為空的屬性名稱 匯出字串陣列
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source){
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for(PropertyDescriptor pd : pds){
            String propertyName = pd.getName();
            if(beanWrapper.getPropertyValue(propertyName) == null){
                nullPropertyNames.add(propertyName);
            }
        }
        System.out.println("ArrayList: " + nullPropertyNames.toString());
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}
