package pl.szawara.apigithub.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.szawara.apigithub.model.Branch;
import pl.szawara.apigithub.model.Commit;
import pl.szawara.apigithub.model.Response;
import pl.szawara.apigithub.service.GithubService;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GitControllerHeaderTest {
    Response res = new Response("repo1","user1",new Branch("branch1",new Commit("sh1")));
    WebTestClient testClient;
    @BeforeEach
    void init(){
        var res=Flux.just(this.res);
        var githubService= Mockito.mock(GithubService.class);
        when(githubService.getFullInfo(anyString())).thenReturn(res);
        testClient=WebTestClient.bindToController(new GitController(githubService)).build();
    }
    @Test
    void getUserRepositoryUnValidHeader() {

        testClient.get().uri("/repositories/user1")
                .header("Accept","application/xml")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_ACCEPTABLE)
                .expectBody();
    }
    @Test
    void getUserRepositoryValidHeader(){
        testClient.get().uri("/repositories/user1")
                .header("Accept","application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].ownerLogin").isEqualTo(res.ownerLogin())
                .jsonPath("$[0].branch.name").isEqualTo(res.branch().name());
    }
}