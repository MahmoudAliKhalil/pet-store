package eg.gov.iti.jets.petstore.exceptions;

import org.springframework.http.HttpStatus;

public class AdminNotFoundException extends BaseException {
    public AdminNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
