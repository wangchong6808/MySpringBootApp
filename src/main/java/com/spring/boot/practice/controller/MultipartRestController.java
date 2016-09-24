package com.spring.boot.practice.controller;

import com.spring.boot.practice.service.MyRestTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangchong on 9/18/16.
 <html>
 <body>
 <form action="http://localhost:8080/app/upload/submit" method="post" enctype="multipart/form-data">
 <input type="file" name="file" />
 <input type="text" name="text" />
 <input type="submit" value="submit" />
 </form>
 </body>
 </html>
 */
@RestController
@RequestMapping(value="/app/upload")
public class MultipartRestController {

    @Autowired
    MyRestTemplate myRestTemplate;

    private static final Logger logger = Logger.getLogger(MultipartRestController.class);

    @RequestMapping(value="/{user_id}", method=RequestMethod.GET)
    public String getUser(@PathVariable Long user_id) {
        String template = "http://localhost:8080/customer/%s";
        String link = String.format(template,String.valueOf(user_id));
        System.out.println(link);
        String result = myRestTemplate.getTemplate().getForObject(link, String.class);
        return result;
    }

    @RequestMapping(value="/submit")
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
