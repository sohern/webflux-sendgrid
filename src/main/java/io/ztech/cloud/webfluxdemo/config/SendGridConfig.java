package io.ztech.cloud.webfluxdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@ConfigurationProperties(prefix = "sendgrid")
@Configuration
@Validated
public class SendGridConfig {
    @NotNull
    private String apiKey;
}
