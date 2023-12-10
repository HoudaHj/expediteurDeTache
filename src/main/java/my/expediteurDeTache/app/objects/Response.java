package my.expediteurDeTache.app.objects;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response implements Serializable {
    private String error;
    private Object response;
}
