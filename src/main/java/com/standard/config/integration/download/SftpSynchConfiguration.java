package com.standard.config.integration.download;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

import java.io.File;

@Slf4j
@Configuration
@EnableIntegration
public class SftpSynchConfiguration {

    public DefaultSftpSessionFactory getSftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost("localhost");
        factory.setPort(2121);
        factory.setAllowUnknownKeys(true);
        factory.setUser("admin");
        factory.setPassword("21232F297A57A5A743894A0E4A801FC3");
        return factory;
    }

    @Bean(name = "sftpInboundFileSynchronizer")
    public SftpInboundFileSynchronizer synchronizer() {
        SftpInboundFileSynchronizer synchronizer = new SftpInboundFileSynchronizer(getSftpSessionFactory());
        synchronizer.setDeleteRemoteFiles(true);
        synchronizer.setRemoteDirectory("upload/done");
        synchronizer.setFilter(new SftpSimplePatternFileListFilter("*.txt"));
        synchronizer.setPreserveTimestamp(true);
        return synchronizer;
    }

    @Bean("sftpMessageSource")
    @InboundChannelAdapter(channel = "fileUpload", poller = @Poller(fixedDelay = "3000"))
    public MessageSource<File> sftpMessageSource() {
        SftpInboundFileSynchronizingMessageSource source = new SftpInboundFileSynchronizingMessageSource(synchronizer());
        source.setLocalDirectory(new File("temp/incoming"));
        source.setAutoCreateLocalDirectory(true);
        return source;
    }

    @ServiceActivator(inputChannel = "fileUpload", autoStartup = "true")
    public void handleIncomingFile(File file) {
        log.debug(String.format("handleIncomingFile BEGIN %s", file.getName()));
        log.debug(String.format("handleIncomingFile END %s", file.getName()));
    }

}
