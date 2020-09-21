package com.standard.config.integration.upload;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.io.File;

@MessagingGateway 
public interface UploadMessagingGateway {
    
    @Gateway(requestChannel = "uploadFile")
    void uploadFile(File file);
    
}
