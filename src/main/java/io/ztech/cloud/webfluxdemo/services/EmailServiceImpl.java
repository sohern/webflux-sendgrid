package io.ztech.cloud.webfluxdemo.services;

import io.ztech.cloud.webfluxdemo.domain.EmailResponse;
import io.ztech.cloud.webfluxdemo.providers.EmailProvider;
import io.ztech.cloud.webfluxdemo.providers.SendGridEmailProvider;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log
@Service
public class EmailServiceImpl implements EmailService {

  /*  @Autowired
    private Message message;*/

  private final EmailProvider emailProvider;

  @Autowired
  public EmailServiceImpl(SendGridEmailProvider emailProvider) {
      this.emailProvider = emailProvider;
  }

  @Override
  public Mono<EmailResponse> sendText(String from, String to, String subject, String body) {

      Mono<EmailResponse> emailResponse = sendEmail(from, to, subject, body);
      log.info(EmailServiceConstants.NON_BLOCKING_SERVICE_MESSAGE);
      return emailResponse;

  }

   /* @Override
   public Mono<EmailResponse> sendHTML(String from, String to, String subject, String body) {

        Mono<EmailResponse> emailResponseMono;
        emailResponseMono = Mono.just(sendEmail(from, to, subject, new Content("text/plain", body)));

        emailResponseMono.subscribe(emailResponse -> log.info("In Service: sendHTML: " + emailResponse.toString()));
        log.info(EmailServiceConstants.NON_BLOCKING_SERVICE_MESSAGE);
        return emailResponseMono;

        //return Mono.just(response);

  }*/

  private Mono<EmailResponse> sendEmail(String from, String to, String subject, String body) {
      log.info("body is : "+ body);
      return this.emailProvider.sendEmail(from, to, subject, body);
  }

}
