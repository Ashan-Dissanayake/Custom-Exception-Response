package lk.ashan.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import lk.ashan.demo.model.response.APIErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<APIErrorResponse> handleExistsException(ResourceExistsException e, HttpServletRequest request) {

        ErrorCode code = ErrorCode.EMPLOYEE_ALREADY_EXISTS;

        APIErrorResponse error = new APIErrorResponse(
                "https://localhost/" + code.getTypeUri(),
                code.getTitle(),
                code.getStatus(),
                code,
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, code.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        ErrorCode code = ErrorCode.EMPLOYEE_NOT_FOUND;

        APIErrorResponse error = new APIErrorResponse(
                "https://localhost" + code.getTypeUri(),
                code.getTitle(),
                code.getStatus(),
                code,
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, code.getStatus());
    }

    // Add generic fallback for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleGenericException(Exception e, HttpServletRequest request) {
        ErrorCode code = ErrorCode.EMPLOYEE_UNKNOWN_ERROR;

        APIErrorResponse error = new APIErrorResponse(
                "https://localhost" + code.getTypeUri(),
                code.getTitle(),
                code.getStatus(),
                code,
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, code.getStatus());
    }
}
