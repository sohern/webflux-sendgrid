package io.ztech.cloud.webfluxdemo.providers;

import io.ztech.cloud.webfluxdemo.domain.EmailResponse;
import reactor.core.publisher.Mono;

public interface EmailProvider {
    public Mono<EmailResponse> sendEmail(String to, String from, String subject, String body);
}
