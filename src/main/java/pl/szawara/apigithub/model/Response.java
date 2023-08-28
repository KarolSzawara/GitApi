package pl.szawara.apigithub.model;

import reactor.core.publisher.Flux;

import java.util.List;

public record Response(String repositoryName, String ownerLogin, Branch branch) {
}
