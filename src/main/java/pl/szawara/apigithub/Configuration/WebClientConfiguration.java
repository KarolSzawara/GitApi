package pl.szawara.apigithub.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfiguration {
    @Bean
    public WebClient webClient(){
        return WebClient.builder().baseUrl("https://api.github.com")
                .defaultHeader("Accept","application/json")
                .build();
    }
}
