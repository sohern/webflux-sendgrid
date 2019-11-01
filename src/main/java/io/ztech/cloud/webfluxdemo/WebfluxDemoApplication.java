package io.ztech.cloud.webfluxdemo;

import io.ztech.cloud.webfluxdemo.config.SendGridConfig;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.reactive.config.EnableWebFlux;

@Log
@EnableConfigurationProperties(SendGridConfig.class)
@SpringBootApplication
@EnableWebFlux
public class WebfluxDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebfluxDemoApplication.class, args);

		//BeerWebClient beerWebClient = new BeerWebClient();
		//beerWebClient.getResult();

		//SendGridClient sendGridClient = new SendGridClient();
		//sendGridClient.getResult();


	}

}
