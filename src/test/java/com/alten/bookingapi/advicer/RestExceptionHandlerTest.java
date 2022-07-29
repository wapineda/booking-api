package com.alten.bookingapi.advicer;

import com.alten.bookingapi.dto.ApiErrorDto;
import com.alten.bookingapi.exception.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class RestExceptionHandlerTest {
    private final RestExceptionHandler handler = new RestExceptionHandler();

    @Test
    void handleBookingDaysException() {
        ResponseEntity response = handler.handleBookingDaysException(new BookingDaysException());

        assertThat(response).satisfies(
                responseEntity -> {
                    assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.EXPECTATION_FAILED);
                    assertThat(responseEntity.getBody()).isInstanceOf(ApiErrorDto.class).extracting("statusCode").isEqualTo(417);
                }
        );
    }

    @Test
    void handleBookingOverstayException() {
        ResponseEntity response = handler.handleBookingOverstayException(new BookingOverstayException());

        assertThat(response).satisfies(
                responseEntity -> {
                    assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.EXPECTATION_FAILED);
                    assertThat(responseEntity.getBody()).isInstanceOf(ApiErrorDto.class).extracting("statusCode").isEqualTo(417);
                }
        );
    }

    @Test
    void handleEarlyBookingException() {
        ResponseEntity response = handler.handleEarlyBookingException(new EarlyBookingException());

        assertThat(response).satisfies(
                responseEntity -> {
                    assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.EXPECTATION_FAILED);
                    assertThat(responseEntity.getBody()).isInstanceOf(ApiErrorDto.class).extracting("statusCode").isEqualTo(417);
                }
        );
    }

    @Test
    void handleRoomAlreadyBookedException() {
        ResponseEntity response = handler.handleRoomAlreadyBookedException(new RoomAlreadyBookedException());

        assertThat(response).satisfies(
                responseEntity -> {
                    assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.EXPECTATION_FAILED);
                    assertThat(responseEntity.getBody()).isInstanceOf(ApiErrorDto.class).extracting("statusCode").isEqualTo(417);
                }
        );
    }

    @Test
    void handleMissingDatesException() {
        ResponseEntity response = handler.handleMissingDatesException(new MissingDatesException());

        assertThat(response).satisfies(
                responseEntity -> {
                    assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.EXPECTATION_FAILED);
                    assertThat(responseEntity.getBody()).isInstanceOf(ApiErrorDto.class).extracting("statusCode").isEqualTo(417);
                }
        );
    }

    @Test
    void handleBookingMissingException() {
        ResponseEntity response = handler.handleBookingMissingException(new BookingMissingException());

        assertThat(response).satisfies(
                responseEntity -> {
                    assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.EXPECTATION_FAILED);
                    assertThat(responseEntity.getBody()).isInstanceOf(ApiErrorDto.class).extracting("statusCode").isEqualTo(417);
                }
        );
    }

    @Test
    void handleDateRangeException() {
        ResponseEntity response = handler.handleDateRangeException(new DateRangeException());

        assertThat(response).satisfies(
                responseEntity -> {
                    assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.EXPECTATION_FAILED);
                    assertThat(responseEntity.getBody()).isInstanceOf(ApiErrorDto.class).extracting("statusCode").isEqualTo(417);
                }
        );
    }
}
