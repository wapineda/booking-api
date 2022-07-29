package com.alten.bookingapi.exception;

public class EarlyBookingException extends RuntimeException {
    public EarlyBookingException() {super("The room should be reserved at least one day in advance");}

}
