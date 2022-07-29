package com.alten.bookingapi.exception;

public class MissingDatesException extends RuntimeException {
    public MissingDatesException() {super("The from and to dates are mandatory");}

}
