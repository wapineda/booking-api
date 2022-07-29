package com.alten.bookingapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErrorDto {
    @NonNull HttpStatus status;
    @NonNull Integer statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Builder.Default
    LocalDateTime timeStamp = LocalDateTime.now();
    @NonNull String message;
}
