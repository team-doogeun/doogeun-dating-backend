package com.project.dugeun.config;

import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class MailConfig {

//    @Value("${mail.smtp.port}")
    private int port;
}
