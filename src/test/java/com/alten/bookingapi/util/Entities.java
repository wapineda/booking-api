package com.alten.bookingapi.util;

import com.alten.bookingapi.entity.Bookings;
import com.alten.bookingapi.entity.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public abstract class Entities {
    public static Bookings randomBook() {
        return Bookings.builder()
                .id(nextInt(1, 10))
                .dateFrom(LocalDate.now())
                .dateTo(LocalDate.now().plusDays(1))
                .cancelled(false)
                .user(randomUser())
                .build();
    }

    public static List<Bookings> randomBooks() {
        List<Bookings> collection = new ArrayList<>();
        int iterations = nextInt(1,10);
        for (int i = 0; i < iterations; i++) {
            collection.add(randomBook());
        }
        return collection;
    }

    public static Users randomUser() {
        return Users.builder()
                .id(nextInt(1,10))
                .cellphoneNumber(randomAlphabetic(10))
                .birthday(LocalDate.now())
                .name(randomAlphabetic(10))
                .email(randomAlphabetic(10))
                .lastName(randomAlphabetic(10))
                .build();
    }

    public static List<Users> randomUsers() {
        List<Users> collection = new ArrayList<>();
        int iterations = nextInt(1,10);
        for (int i = 0; i < iterations; i++) {
            collection.add(randomUser());
        }
        return collection;
    }
}
