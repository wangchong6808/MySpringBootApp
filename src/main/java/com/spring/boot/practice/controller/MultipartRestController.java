package com.spring.boot.practice.controller;

import com.spring.boot.practice.model.Contract;
import com.spring.boot.practice.repository.ContractRepository;
import com.spring.boot.practice.service.MyRestTemplate;
import org.apache.log4j.Logger;
import org.bson.ByteBuf;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Date;

/**
 * Created by wangchong on 9/18/16.
 * <html>
 * <body>
 * <form action="http://localhost:8080/app/contract" method="post" enctype="multipart/form-data">
 * <input type="file" name="file" />
 * <input type="text" name="text" />
 * <input type="submit" value="submit" />
 * </form>
 * </body>
 * </html>
 */
@RestController
@RequestMapping(value = "/app/contract")
public class MultipartRestController {

    @Autowired
    MyRestTemplate myRestTemplate;

    @Autowired
    ContractRepository contractRepository;

    private static final Logger logger = Logger.getLogger(MultipartRestController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getContract(HttpServletResponse response, OutputStream outputStream, @PathVariable Long id) throws IOException {
        Contract contract = contractRepository.findOne(String.valueOf(id));
        response.setContentType(MediaType.IMAGE_JPEG.getType());
        response.setHeader("content-disposition", "inline; filename=" + contract.getFileName());
        outputStream.write(contract.getContent().getData());
        //contract.setContent(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file, @RequestParam String text) {

        String name = file.getOriginalFilename();
        long size = file.getSize();
        if (size >= 15728640l) {
            //16793600 max allowed document size
            return "文件不能大于15M！";
        }
        logger.info("text = " + text + " name=" + name + " size=" + size);

        /*try (OutputStream out = new BufferedOutputStream(new FileOutputStream("/Users/wangchong/IdeaProjects/MySpringBootApp" + File.separator + name))) {
            BufferedInputStream in = new BufferedInputStream(file.getInputStream());
            //ByteBuffer bf = ByteBuffer.allocate(size);
            byte[] bytes = new byte[1024];
            int i;
            while ((i = in.read(bytes)) !=-1){
                out.write(bytes);
            }
            //Binary binary = new Binary();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        byte[] bytes = new byte[0];
        Binary binary = null;
        try {
            bytes = file.getBytes();
            binary = new Binary(bytes);
            Contract contract = new Contract();
            contract.setId("1");
            contract.setCreateDate(new Date());
            contract.setContent(binary);
            contract.setFileName(name);
            contract.setUserID("123");
            contract.setDescription(text);
            contractRepository.save(contract);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try (
             OutputStream out = new FileOutputStream("/Users/wangchong/IdeaProjects/MySpringBootApp" + File.separator + name);
        ){
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        logger.info("uploaded............");
        return "ok";
    }

}
