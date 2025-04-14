package albin2501.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", e.getStatusCode().value());
        response.put("message", e.getReason());

        return new ResponseEntity<>(response, e.getStatusCode());
    }
}
