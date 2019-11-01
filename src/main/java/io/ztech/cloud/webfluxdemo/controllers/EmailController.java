package io.ztech.cloud.webfluxdemo.controllers;

import io.ztech.cloud.webfluxdemo.domain.EmailResponse;
import io.ztech.cloud.webfluxdemo.services.EmailService;
import io.ztech.cloud.webfluxdemo.services.EmailServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log
@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    /*@GetMapping("/sendHTML")
    public Mono<EmailResponse> sendHTML() {
        log.info(EmailControllerConstants.IN_SEND_HTML_MESSAGE);
        return emailService
                .sendHTML("sohern@gmail.com", "sohern@ztech.io", "My test subject","how's it going?");

    }*/

    @GetMapping("/sendText")
    public Mono<EmailResponse> sendText() {
        log.info(EmailControllerConstants.IN_SEND_TEXT_MESSAGE);
        //Mono<Message> message = request.bodyToMono(Message.class);

        Mono<EmailResponse> emailResponseMono;
        emailResponseMono = emailService
                .sendText("sohern@gmail.com", "sohern@ztech.io", "My test subject", "message3");


        log.info(EmailControllerConstants.NON_BLOCKING_CONTROLLER_MESSAGE);
        return emailResponseMono;
    }

}
