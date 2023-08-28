package pl.szawara.apigithub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class Message {
    String Message;
    HttpStatus status;
}
