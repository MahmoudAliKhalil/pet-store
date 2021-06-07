package eg.gov.iti.jets.petstore.exceptions;

import org.springframework.http.HttpStatus;


public class SpeciesException extends BaseException {
    public SpeciesException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

