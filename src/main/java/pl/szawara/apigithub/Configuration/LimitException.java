package pl.szawara.apigithub.Configuration;

public class LimitException extends RuntimeException {
    public LimitException(String message){
        super(message);
    }
}
