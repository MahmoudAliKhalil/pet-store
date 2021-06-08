package eg.gov.iti.jets.petstore.handlers;


import eg.gov.iti.jets.petstore.exceptions.BaseException;
import eg.gov.iti.jets.petstore.exceptions.models.ErrorDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllersExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ErrorDetails exceptionHandler(BaseException exception, WebRequest request) {
        return new ErrorDetails(exception.getStatus().toString(), request.getDescription(false), exception.getMessage());
    }
}
