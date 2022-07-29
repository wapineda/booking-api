package com.alten.bookingapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserDto {
    String name;
    String lastName;
    LocalDate birthday;
    String email;
    String cellphoneNumber;
}
