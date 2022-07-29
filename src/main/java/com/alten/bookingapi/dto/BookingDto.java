package com.alten.bookingapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BookingDto {
    Integer id;
    UserDto user;
    LocalDate dateFrom;
    LocalDate dateTo;
    Boolean cancelled;
}
