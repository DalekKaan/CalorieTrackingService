package ru.r1b.calorietrackingservice.http.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.r1b.calorietrackingservice.http.scheme.error.RestError;
import ru.r1b.calorietrackingservice.http.scheme.error.ValidationRestError;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(
            ResponseStatusException ex) {
        RestError apiError = new RestError((HttpStatus) ex.getStatusCode(), ex.getReason(), ex);
        return buildResponseEntity(apiError);
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Object[] args = new Object[]{ex.getPropertyName(), ex.getValue()};
        String var10000 = String.valueOf(args[0]);
        String defaultDetail = "Failed to convert '" + var10000 + "' with value: '" + args[1] + "'";
        RestError apiError = new RestError(BAD_REQUEST, defaultDetail, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex) {
        var errorData = ex.getConstraintViolations().iterator().next();
        ValidationRestError restError = new ValidationRestError(
                BAD_REQUEST, ex.getMessage(), ex,
                errorData.getRootBeanClass().getSimpleName(),
                errorData.getPropertyPath().toString(),
                errorData.getInvalidValue(),
                errorData.getMessage()
        );
        return buildResponseEntity(restError);
    }

    private ResponseEntity<Object> buildResponseEntity(RestError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
