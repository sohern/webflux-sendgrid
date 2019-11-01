package io.ztech.cloud.webfluxdemo.services;

import io.ztech.cloud.webfluxdemo.domain.EmailResponse;
import io.ztech.cloud.webfluxdemo.domain.Message;
import reactor.core.publisher.Mono;

public interface EmailService {
    Mono<EmailResponse> sendText(String from, String to, String subject, String body);
    // Mono<EmailResponse> sendHTML(String from, String to, String subject, String body);
}
