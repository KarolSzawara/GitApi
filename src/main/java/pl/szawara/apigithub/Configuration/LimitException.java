package pl.szawara.apigithub.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LimitException extends RuntimeException {
    public LimitException(String message){
        super(message);
    }
}
