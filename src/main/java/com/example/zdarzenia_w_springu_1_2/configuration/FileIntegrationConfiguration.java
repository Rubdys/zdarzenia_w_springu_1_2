package com.example.zdarzenia_w_springu_1_2.configuration;

import com.example.zdarzenia_w_springu_1_2.transformer.FileTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.Message;

import java.io.File;

@Configuration
public class FileIntegrationConfiguration {

    @Bean
    IntegrationFlow fileIntegrationFlow(
            FileReadingMessageSource fileAdapter,
            FileTransformer transformer,
            FileWritingMessageHandler handler
    ){
        return IntegrationFlows.from(fileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
                .transform(transformer, "getData")
                .handle(handler)
                .get();
    }

    @Bean
    FileTransformer transformer(){
        return new FileTransformer();
    }

    @Bean
    FileReadingMessageSource fileAdapter(){
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("data/input"));

        return fileSource;
    }

    @Bean
    FileWritingMessageHandler outputFileAdapter(){
        File dir = new File("data/output");
        FileWritingMessageHandler handler = new FileWritingMessageHandler(dir);
        handler.setFileNameGenerator(new FileNameGenerator() {
            @Override
            public String generateFileName(Message<?> message) {
                return "list.txt";
            }
        });
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setExpectReply(false);

        return handler;
    }

}
