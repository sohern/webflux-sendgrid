package io.ztech.cloud.webfluxdemo.providers;

import com.sendgrid.Method;
import com.sendgrid.Response;
import io.ztech.cloud.webfluxdemo.builders.SendGridBuilders.*;
import io.ztech.cloud.webfluxdemo.config.SendGridConfig;
import io.ztech.cloud.webfluxdemo.domain.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Component
public class SendGridEmailProvider implements EmailProvider {

    private final SendGrid sendGrid;

    @Autowired
    public SendGridEmailProvider(SendGridConfig sendGridConfig) {
        this.sendGrid = SendGrid.sendGridBuilder()
                .apiKey(sendGridConfig.getApiKey())
                .build();
    }

    @Override
    public Mono<EmailResponse> sendEmail(String to, String from, String subject, String body) {

        Content content = Content.contentBuilder()
                .type(EmailProviderConstants.Types.TEXT_PLAIN)
                .value(body)
                .build();

        Mail mail = Mail.mailBuilder()
                .to(Email.emailBuilder()
                        .email(to)
                        .build())
                .from(Email.emailBuilder()
                        .email(from)
                        .build())
                .content(content)
                .subject(subject)
                .build();

        Content moreContent = Content.contentBuilder()
                .type(EmailProviderConstants.Types.TEXT_HTML)
                .value("<H1>Another line of content.</H1>")
                .build();

        mail.addContent(moreContent);

        mail.setReplyTo(Email.emailBuilder().email("sohern@gmail.com").build());

        Request request = Request.builder().build();

        Mono<EmailResponse> blockingWrapper = Mono.fromCallable(() -> {
            request.setMethod(Method.POST);
            request.setEndpoint(EmailProviderConstants.EndPoints.MAIL_SEND);
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

            return EmailResponse.builder()
                    .body(response.getBody())
                    .statusCode(response.getStatusCode())
                    .headers(response.getHeaders().toString())
                    .build();
        });

        // Dedicate the mono to boundedElastic (event loop).
        blockingWrapper.subscribeOn(Schedulers.boundedElastic());

        blockingWrapper.retryBackoff(
                EmailProviderConstants.NUM_RETRIES,
                Duration.ofMillis(EmailProviderConstants.DURATION_OF_MILLISECONDS))
                    .doOnError(System.out::println);

        // Return wrapper for blocking api.
        return blockingWrapper;

    }

}
