package com.daimler.otr.controller;

import com.daimler.otr.configuration.Constants;
import com.daimler.otr.credential.UserIdentification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ControllerHelper {

    private static final Logger logger = Logger.getLogger(ControllerHelper.class);

    UserIdentification extractUserIdentification(HttpServletRequest request) {
        if (request == null ){
            return null;
        }
        String credentialString = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(credentialString)){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserIdentification credential = mapper.readValue(credentialString, UserIdentification.class);
            return StringUtils.hasText(credential.getUserId()) && StringUtils.hasText(credential.getDealerId())? credential : null;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    void normalizeDataForFrondEnd(Document document){
        transformDateToString(document);
    }

    private void transformDateToString(Document document) {
        Map<String, Date> map = new HashMap<>();
        document.keySet().forEach(key -> {
            Object value = document.get(key);
            if (value instanceof Date) {
                map.put(key, (Date) value);
            }
        });

        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_FOR_FRONTEND);
        map.entrySet().forEach(entry -> {
            document.put(entry.getKey(), format.format(entry.getValue()));
        });
    }
}
