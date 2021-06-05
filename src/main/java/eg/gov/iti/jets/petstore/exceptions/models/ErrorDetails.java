package eg.gov.iti.jets.petstore.exceptions.models;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ErrorDetails {
    @NonNull
    private String code;
    private LocalDateTime date = LocalDateTime.now();
    @NonNull
    private String uri;
    @NonNull
    private String message;
}
