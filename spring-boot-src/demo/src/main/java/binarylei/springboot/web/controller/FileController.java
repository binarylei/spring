package binarylei.springboot.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author: leigang
 * @version: 2018-09-01
 */
@Controller
public class FileController {

    @Value("${web.images-path}")
    private String path;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/upload")
    public void upload(@RequestParam(name = "head-img") MultipartFile file, HttpServletRequest request) {
        String filename = file.getOriginalFilename();
        try {
            file.transferTo(new File("C:\\Users\\len\\Desktop\\xdr\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/hot")
    @ResponseBody
    public String host() {
        System.out.println(path);
        return "index";
    }

}
