package com.spring.boot.practice.controller;

import com.spring.boot.practice.service.MyRestTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by wangchong on 9/18/16.
 */
@RestController
@RequestMapping(value="/app/base")
public class MyRestController {

    @Autowired
    MyRestTemplate myRestTemplate;

    private static final Logger logger = Logger.getLogger(MyRestController.class);

    @RequestMapping(value="/{user_id}", method=RequestMethod.GET)
    public String getUser(@PathVariable Long user_id) {
        String template = "http://localhost:8080/app/customer/%s";
        String link = String.format(template,String.valueOf(user_id));
        logger.info(link);
        String result = myRestTemplate.getTemplate().getForObject(link, String.class);
        return result;
    }

    @RequestMapping(value="/upload")
    public String uploadFile(@RequestParam MultipartFile file,@RequestParam String text){

        String name = file.getOriginalFilename();
        long size = file.getSize()/1024;
        logger.info("text = "+text+" name="+name+" size="+size);
        /*try (OutputStream out = new BufferedOutputStream(new FileOutputStream("/Users/wangchong/IdeaProjects/MySpringBootApp" + File.separator + name))) {
            BufferedInputStream in = new BufferedInputStream(file.getInputStream());
            byte[] bytes = new byte[1024];
            int i;
            while ((i = in.read(bytes)) !=-1){
                out.write(bytes);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        logger.info("uploaded............");
    return "ok";
    }

}
