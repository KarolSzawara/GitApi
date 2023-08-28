package pl.szawara.apigithub.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szawara.apigithub.model.Response;
import pl.szawara.apigithub.service.GithubService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/repositories",produces = "application/json")

public class GitController {
    private final GithubService githubService;
    public GitController(GithubService githubService){
        this.githubService=githubService;
    }
    @GetMapping("/{username}")
    Flux<Response> getUserRepository(@PathVariable String username){
        return githubService.getFullInfo(username);
    }


}
