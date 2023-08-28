package pl.szawara.apigithub.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.szawara.apigithub.service.GithubService;
import pl.szawara.apigithub.Configuration.NotFoundException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class GitControllerExceptionTest {
 String user="userNotFound";
    WebTestClient testClient;
    @BeforeEach
    void init(){

        var githubService= Mockito.mock(GithubService.class);
        when(githubService.getFullInfo(anyString())).thenThrow(new NotFoundException("Not found"));
        testClient= WebTestClient.bindToController(new GitController(githubService)).build();
    }
    @Test
    void userNotFoundTest(){
        testClient.get().uri("/repositories/{user}",user)
                .exchange().expectStatus().isNotFound();
    }
}
