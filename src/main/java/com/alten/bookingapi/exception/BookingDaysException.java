package com.alten.bookingapi.exception;

public class BookingDaysException extends RuntimeException {
    public BookingDaysException() {super("You can not book the room for over three days");}

}
