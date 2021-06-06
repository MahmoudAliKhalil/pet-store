package eg.gov.iti.jets.petstore.exceptions;

import org.springframework.http.HttpStatus;

public class CategoryException extends BaseException{
    public CategoryException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
