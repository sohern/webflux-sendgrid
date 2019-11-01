package io.ztech.cloud.webfluxdemo.builders;

import lombok.*;

public class SendGridBuilders {

    @Builder
    public static class Request extends com.sendgrid.Request { }

    @Getter
    public static class Mail extends com.sendgrid.Mail {
        @Builder(builderMethodName = "mailBuilder")
        public Mail(Email from, String subject, Email to, Content content) {
            super(from, subject, to, content);
        }
    }

    @Getter
    @Setter
    public static class Email extends com.sendgrid.Email {
        @Builder(builderMethodName = "emailBuilder")
        Email(String email) {
            super(email);
        }
    }

    @Getter
    public static class SendGrid extends com.sendgrid.SendGrid {
        @Builder(builderMethodName = "sendGridBuilder")
        SendGrid(String apiKey) {
            super(apiKey);
        }

    }

    @Getter
    @Setter
    public static class Content extends com.sendgrid.Content {
        @Builder(builderMethodName = "contentBuilder")
        Content(String type, String value) {
            super(type, value);
        }

    }

}
