package com.example.bankcards.exception;

import com.example.bankcards.dto.common.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex, WebRequest request){
        log.warn("Bad credentials attempt: {}", ex.getMessage());

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("Invalid username or password")
                .code("BAD_CREDENTIALS")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request){
        log.warn("User already exists: {}", ex.getUsername());

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("User with username '" + ex.getUsername() + "' already exists")
                .code("USER_ALREADY_EXISTS")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        log.warn("User not found: {}", ex.getUserId());

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("User with ID " + ex.getUserId() + " not found")
                .code("USER_NOT_FOUND")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCardNotFoundException(CardNotFoundException ex, WebRequest request){
        log.warn("Card not found: {}", ex.getCardId());

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("Card with ID " + ex.getCardId() + " not found")
                .code("CARD_NOT_FOUND")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException ex, WebRequest request){
        log.warn("Insufficient balance for transaction");

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("Insufficient balance to complete the transaction")
                .code("INSUFFICIENT_BALANCE")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(SameCardTransferException.class)
    public ResponseEntity<ApiErrorResponse> handleSameCardTransferException(SameCardTransferException ex, WebRequest request){
        log.warn("Attempt to transfer to the same card");

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("Cannot transfer to the same card")
                .code("SAME_CARD_TRANSFER")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(CardStatusChangeException.class)
    public ResponseEntity<ApiErrorResponse> handleCardStatusChangeException(CardStatusChangeException ex, WebRequest request){
        log.warn("Card status change error: {}", ex.getMessage());

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message(ex.getMessage())
                .code("CARD_STATUS_ERROR")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("Validation failed :" + errors.toString())
                .code("VALIDATION_ERROR")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, WebRequest request){
        log.warn("Unexpected error: ", ex);

        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("Internal server error")
                .code("INTERNAL_ERROR")
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
