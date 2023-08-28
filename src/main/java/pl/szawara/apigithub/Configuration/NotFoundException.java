package pl.szawara.apigithub.Configuration;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
