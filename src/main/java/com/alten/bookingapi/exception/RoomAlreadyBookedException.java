package com.alten.bookingapi.exception;

public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException() {super("The room is already booked in the selected dates");}

}
