package com.alten.bookingapi.exception;

public class BookingMissingException extends RuntimeException {
    public BookingMissingException() {super("The requested booking does not exists");}

}
