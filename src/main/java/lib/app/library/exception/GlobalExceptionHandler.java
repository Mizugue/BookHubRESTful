package lib.app.library.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorResponse> myMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        MyErrorResponse CategoryError = new MyErrorResponse();
        CategoryError.setStatus(HttpStatus.NOT_FOUND.value());
        String errorMessage = exc.getBindingResult().getFieldErrors()
                .stream().map(error -> error.getField() + " " +
                        error.getDefaultMessage()).collect(Collectors.joining(", "));
        CategoryError.setMessage(errorMessage);
        CategoryError.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(CategoryError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIErrorResponse> myResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        APIErrorResponse apiResponse = new APIErrorResponse(resourceNotFoundException.getMessage(), false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIErrorResponse> myAPIException(APIException apiException){
        return new ResponseEntity<APIErrorResponse>
                (new APIErrorResponse(apiException.getMessage(), false), HttpStatus.OK);
    }





}
