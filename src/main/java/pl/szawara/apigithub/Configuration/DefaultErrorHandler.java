package pl.szawara.apigithub.Configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.server.ServerWebExchange;
import pl.szawara.apigithub.model.Message;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class DefaultErrorHandler extends ResponseEntityExceptionHandler {
    @Override
    protected Mono<ResponseEntity<Object>> handleNotAcceptableStatusException(NotAcceptableStatusException ex, HttpHeaders headers, HttpStatusCode status, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Not Acceptable Status. The server cannot produce a response matching the list of acceptable values in the 'Accept' header. Please ensure the header is set to 'application/json'."));
    }
}
