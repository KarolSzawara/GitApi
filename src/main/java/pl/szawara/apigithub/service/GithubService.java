package pl.szawara.apigithub.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.szawara.apigithub.Configuration.NotFoundException;
import pl.szawara.apigithub.model.Branch;
import pl.szawara.apigithub.model.Repository;
import pl.szawara.apigithub.model.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GithubService {

    private WebClient webClient;
    public GithubService(WebClient webClient){
        this.webClient=webClient;
    }
    private Flux<Repository> getUserRepositories(String userName){
        Flux<Repository> ret= webClient.get()
                .uri("/users/{userName}/repos",userName)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode== HttpStatus.NOT_FOUND,
                        clientResponse -> Mono.just(new NotFoundException("Repository not found for user:"+userName)))
                .bodyToFlux(Repository.class)
                .filter(repository -> !repository.fork())
                ;

       return ret;

    }

    public Flux<Response> getFullInfo(String userName){
        return getUserRepositories(userName).flatMap(repository -> {
           return webClient.get()
                   .uri("/repos/KarolSzawara/Shop/branches")
                   .retrieve()
                   .onStatus(httpStatusCode -> httpStatusCode== HttpStatus.NOT_FOUND,
                           clientResponse -> Mono.just(new NotFoundException("Repository not found for user:"+userName)))
                   .bodyToFlux(Branch.class)
                    .map(branch-> {
                        return new Response(repository.name(),userName,branch);
                    })
                   ;
        });

    }

}
