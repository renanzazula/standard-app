package com.standard.controller;

import com.standard.config.integration.upload.UploadMessagingGateway;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UploadRestController {
    
    private final UploadMessagingGateway gateway;
     
    @GetMapping("public/api/upload/{content}")
    public String uploadFile(@PathVariable String content, @RequestParam("extra") String extra) throws IOException {
        String resultContent = "AH:" + content;

        String fileName = String.format("mytextfile_%s.txt", LocalDateTime.now());
        
        File file = new File("temp/incoming/" + fileName);
        if(file.exists()){
            file.delete();
        }
        
        FileUtils.writeStringToFile(file, resultContent, "UTF-8");
        
        gateway.uploadFile(file);
        return resultContent;
    }
}
