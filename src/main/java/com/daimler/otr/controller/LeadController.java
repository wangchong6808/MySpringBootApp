package com.daimler.otr.controller;

import com.daimler.otr.enums.LeadAttribute;
import com.daimler.otr.service.LeadService;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class LeadController {

    private static final Logger logger = Logger.getLogger(LeadController.class);

    @Autowired
    LeadService leadService;

    @RequestMapping(value="/lead", method= RequestMethod.POST)
    public String createLead(@RequestParam(required = true) String user_name, @RequestParam(required = true) String lead_info){
        logger.info("create lead is called......... user_name:"+user_name);
        logger.info("lead info is " +lead_info);
        Document lead = Document.parse(lead_info);
        lead.append(LeadAttribute.creation_date.name(),new Date());
        leadService.createLead(lead);
        return "OK";
    }

}
