package com.alten.bookingapi.controller;

import com.alten.bookingapi.dto.BookingDto;
import com.alten.bookingapi.entity.Bookings;
import com.alten.bookingapi.entity.Users;
import com.alten.bookingapi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("all")
    public ResponseEntity<List<Bookings>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("checkAvailability")
    public ResponseEntity<String> checkRoomAvailability() {
        return ResponseEntity.ok(bookingService.checkRoomAvailability());
    }

    @GetMapping("current")
    public ResponseEntity<List<Bookings>> getCurrentBookings() {
        return ResponseEntity.ok(bookingService.getCurrentBookings());
    }

    @GetMapping("users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(bookingService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingDto booking) {
        bookingService.createBooking(booking);
        return ResponseEntity.ok("Booking created");
    }

    @PutMapping
    public ResponseEntity<String> updateBooking(@RequestBody BookingDto booking) {
        bookingService.updateBooking(booking);
        return ResponseEntity.ok("Booking updated");
    }

    @PutMapping("cancel")
    public ResponseEntity<String> cancelBooking(@RequestParam("id") int id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled");
    }
}
