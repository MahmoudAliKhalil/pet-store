package eg.gov.iti.jets.petstore.handlers;


import eg.gov.iti.jets.petstore.exceptions.ProductNotFoundException;
import eg.gov.iti.jets.petstore.exceptions.models.ErrorDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllersExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDetails customerNotFoundHandler(ProductNotFoundException exception, WebRequest request) {
        return new ErrorDetails(exception.getStatus().toString(), request.getDescription(false), exception.getMessage());
    }
}
