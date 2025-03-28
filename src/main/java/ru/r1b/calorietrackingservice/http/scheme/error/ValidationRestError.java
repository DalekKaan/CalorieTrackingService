package ru.r1b.calorietrackingservice.http.scheme.error;


import org.springframework.http.HttpStatus;

public class ValidationRestError extends RestError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ValidationRestError(HttpStatus status, String object, String field, Object rejectedValue, String message) {
        super(status);
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public ValidationRestError(HttpStatus status, Throwable ex, String object, String field, Object rejectedValue, String message) {
        super(status, ex);
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public ValidationRestError(HttpStatus status, String message, Throwable ex, String object, String field, Object rejectedValue, String message1) {
        super(status, message, ex);
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message1;
    }

    public String getObject() {
        return object;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
