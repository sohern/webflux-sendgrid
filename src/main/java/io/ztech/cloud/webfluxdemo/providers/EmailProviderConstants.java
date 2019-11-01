package io.ztech.cloud.webfluxdemo.providers;

class EmailProviderConstants {
    final static int NUM_RETRIES = 5;
    final static int DURATION_OF_MILLISECONDS = 100;

    static class Types {
        final static String TEXT_PLAIN = "text/plain";
        final static String TEXT_HTML = "text/html";
    }

    static class EndPoints {
        final static String MAIL_SEND = "mail/send";
    }
}