package com.alten.bookingapi.exception;

public class DateRangeException extends RuntimeException {
    public DateRangeException() {super("From date should be lesser than to date");}
}
