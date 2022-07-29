package com.alten.bookingapi.advicer;

import com.alten.bookingapi.dto.ApiErrorDto;
import com.alten.bookingapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BookingDaysException.class)
    ResponseEntity handleBookingDaysException(BookingDaysException ex) {
        HttpStatus status = HttpStatus.EXPECTATION_FAILED;
        return ResponseEntity.status(status).body(
                ApiErrorDto.builder()
                        .status(status)
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(BookingOverstayException.class)
    ResponseEntity handleBookingOverstayException(BookingOverstayException ex) {
        HttpStatus status = HttpStatus.EXPECTATION_FAILED;
        return ResponseEntity.status(status).body(
                ApiErrorDto.builder()
                        .status(status)
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(EarlyBookingException.class)
    ResponseEntity handleEarlyBookingException(EarlyBookingException ex) {
        HttpStatus status = HttpStatus.EXPECTATION_FAILED;
        return ResponseEntity.status(status).body(
                ApiErrorDto.builder()
                        .status(status)
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(RoomAlreadyBookedException.class)
    ResponseEntity handleRoomAlreadyBookedException(RoomAlreadyBookedException ex) {
        HttpStatus status = HttpStatus.EXPECTATION_FAILED;
        return ResponseEntity.status(status).body(
                ApiErrorDto.builder()
                        .status(status)
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(MissingDatesException.class)
    ResponseEntity handleMissingDatesException(MissingDatesException ex) {
        HttpStatus status = HttpStatus.EXPECTATION_FAILED;
        return ResponseEntity.status(status).body(
                ApiErrorDto.builder()
                        .status(status)
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(BookingMissingException.class)
    ResponseEntity handleBookingMissingException(BookingMissingException ex) {
        HttpStatus status = HttpStatus.EXPECTATION_FAILED;
        return ResponseEntity.status(status).body(
                ApiErrorDto.builder()
                        .status(status)
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(DateRangeException.class)
    ResponseEntity handleDateRangeException(DateRangeException ex) {
        HttpStatus status = HttpStatus.EXPECTATION_FAILED;
        return ResponseEntity.status(status).body(
                ApiErrorDto.builder()
                        .status(status)
                        .statusCode(status.value())
                        .message(ex.getMessage())
                        .build());
    }
}
