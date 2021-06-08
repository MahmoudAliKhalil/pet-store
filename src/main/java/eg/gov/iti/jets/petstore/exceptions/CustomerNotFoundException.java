package eg.gov.iti.jets.petstore.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BaseException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}