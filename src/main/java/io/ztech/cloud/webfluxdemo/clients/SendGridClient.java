package io.ztech.cloud.webfluxdemo.clients;

import lombok.extern.java.Log;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

@Log
public class SendGridClient {

    private ExchangeFunction exchange = ExchangeFunctions.create(new ReactorClientHttpConnector());

    private static final String HOST = "localhost";

    private static final int PORT = 8080;

    private WebClient client = WebClient.create(String.format("http://%s:%d", HOST, PORT));

    private Mono<String> result =
            client.get()
            .uri("/sendText")
            .exchange()
            .flatMap(res -> res
                    .bodyToMono(String.class));

    public void getResult() {
        result.doOnNext(System.out::println)
                .block();

/*
        log.info((response
                .block()).statusCode().toString());*/
    }


}
