package com.alten.bookingapi.exception;

public class BookingOverstayException extends RuntimeException {
    public BookingOverstayException() {super("You can not reserve the room more than 30 days in advance");}

}
