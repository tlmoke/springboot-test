package com.tl.springboottest.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {
    private Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String file() {
        logger.info("跳转文件上传控制器");
        return "file";
    }

    @RequestMapping(value = "/multifile", method = RequestMethod.GET)
    public String file2() {
        logger.info("跳转文件上传控制器");
        return "multifile";
    }

    /**
     * 文件上传控制器
     *
     * @ param file
     * @ return
     * @ throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {// 文件上传
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(new File(file.getOriginalFilename())));
        logger.info("文件名称：" + file.getOriginalFilename());
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        return "文件上传成功";
    }

    /**
     * 多文件上传控制器
     */
    @RequestMapping(value = "multifileupload", method = RequestMethod.POST)
    public @ResponseBody String upload(HttpServletRequest servletRequest) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) servletRequest).getFiles("file");
        for (MultipartFile multipartFile : files) {
            try {
                if (!StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
                    BufferedOutputStream outputStream = new BufferedOutputStream(
                            new FileOutputStream(new File(multipartFile.getOriginalFilename())));
                    outputStream.write(multipartFile.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "文件上传失败";
            } catch (IOException e) {
                e.printStackTrace();
                return "文件上传失败";
            }
        }
        return "文件上传成功";
    }
}
