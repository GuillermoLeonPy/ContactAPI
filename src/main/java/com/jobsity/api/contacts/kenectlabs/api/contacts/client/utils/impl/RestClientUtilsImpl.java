package com.jobsity.api.contacts.kenectlabs.api.contacts.client.utils.impl;

import com.jobsity.api.contacts.kenectlabs.api.contacts.client.utils.RestClientUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class RestClientUtilsImpl implements RestClientUtils {


    public String extractHeader(HttpHeaders headers, String key){
        List<String> values = headers.getValuesAsList(key);
        return values != null && !values.isEmpty() ? values.get(0) : "";
    }

    public int getNumberValueHeader(HttpHeaders headers, String key){
        String value = extractHeader(headers,key);
        if (StringUtils.hasText(value)){
            try{
                return Integer.parseInt(value);
            }catch (Exception e){
                return 0;
            }
        }else{
            return 0;
        }
    }
}
