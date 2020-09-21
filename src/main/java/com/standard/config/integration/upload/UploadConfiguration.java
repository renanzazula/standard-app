package com.standard.config.integration.upload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.MessageHandler;

import java.time.LocalDateTime;

@Configuration
public class UploadConfiguration {
    public DefaultSftpSessionFactory getSftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost("localhost");
        factory.setPort(2121);
        factory.setAllowUnknownKeys(true);
        factory.setUser("admin");
        factory.setPassword("21232F297A57A5A743894A0E4A801FC3");
        return factory;
    }
    
    @Bean
    @ServiceActivator(inputChannel = "uploadFile")
    public MessageHandler uploadHandler(){
        SftpMessageHandler sftpMessageHandler = new SftpMessageHandler(getSftpSessionFactory());
        sftpMessageHandler.setRemoteDirectoryExpression(new LiteralExpression("/upload"));
        sftpMessageHandler.setFileNameGenerator(message -> String.format("mytextfile_%s.txt", LocalDateTime.now()));
        return sftpMessageHandler;
    }
    
    
}
