package com.alten.bookingapi.service;

import com.alten.bookingapi.dto.BookingDto;
import com.alten.bookingapi.dto.UserDto;
import com.alten.bookingapi.entity.Bookings;
import com.alten.bookingapi.entity.Users;
import com.alten.bookingapi.exception.*;
import com.alten.bookingapi.repository.BookingRepo;
import com.alten.bookingapi.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;

    /**
     * Gets all bookings in the database
     * @return a list of bookings
     */
    public List<Bookings> getAllBookings() {
        return bookingRepo.findAll();
    }

    /**
     * Gets all users in the database
     * @return a list of users
     */
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Check room availability
     * @return a message with the room availability
     */
    public String checkRoomAvailability() {
        List<Bookings> activeBookings = bookingRepo.findAllByCancelledFalseOrCancelledIsNull();
        if (activeBookings.size() > 0) {
            StringBuilder message = new StringBuilder("Room is available for booking outside the following dates: ");
            for (Bookings booking: activeBookings)
                message.append(String.format("%s - %s | ", booking.getDateFrom(), booking.getDateTo()));
            return message.toString();
        }
        return "Room is available for booking";
    }

    /**
     * Get current bookings
     * @return a list of current bookings (matching with current date)
     */
    public List<Bookings> getCurrentBookings() {
        return bookingRepo.findActiveBookings(LocalDate.now());
    }

    /**
     * Creates a new user
     * @param user new user details
     * @return a newly created user
     */
    public Users createUser(UserDto user) {
        Optional<Users> exists = userRepo.findByEmail(user.getEmail());
        return exists.orElseGet(() -> userRepo.save(Users.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .cellphoneNumber(user.getCellphoneNumber())
                .build()));
    }

    /**
     * Creates a new booking
     * @param booking new booking details
     */
    public void createBooking(BookingDto booking) {
        validateBooking(booking);

        bookingRepo.save(Bookings.builder()
                        .user(createUser(booking.getUser()))
                        .dateFrom(booking.getDateFrom())
                        .dateTo(booking.getDateTo())
                        .cancelled(false)
                        .build());
    }

    /**
     * Cancels the provided booking
     * @param id the booking id to be cancelled
     */
    public void cancelBooking(int id) {
        updateBooking(BookingDto.builder()
                .id(id)
                .cancelled(true)
                .build());
    }

    /**
     * Updates an existing booking
     * @param booking the booking to be updated
     */
    public void updateBooking(BookingDto booking) {

        Bookings existingBooking = bookingRepo.findById(booking.getId()).orElseThrow(BookingMissingException::new);

        existingBooking.setCancelled(booking.getCancelled());

        if (!existingBooking.getCancelled()) {
            if (booking.getDateFrom() != null)
                existingBooking.setDateFrom(booking.getDateFrom());
            if (booking.getDateTo() != null)
                existingBooking.setDateTo(booking.getDateTo());

            validateBooking(BookingDto.builder()
                    .dateFrom(existingBooking.getDateFrom())
                    .dateTo(existingBooking.getDateTo())
                    .build());
        }

        bookingRepo.save(existingBooking);
    }

    /**
     * Checks that the given booking is valid
     * @param booking the booking to be validated
     */
    private void validateBooking(BookingDto booking) {
        //Validate dates
        if (booking.getDateFrom() == null || booking.getDateTo() == null)
            throw new MissingDatesException();

        //Validate dates
        if (booking.getDateFrom().compareTo(booking.getDateTo()) > 0)
            throw new DateRangeException();

        //The booking shouldn't be longer than 3 days
        long diff = ChronoUnit.DAYS.between(booking.getDateFrom(), booking.getDateTo());
        if (diff > 3)
            throw new BookingDaysException();

        //The room can not be booked more than 30 day in advance
        diff = ChronoUnit.DAYS.between(LocalDate.now(), booking.getDateFrom());
        if (diff > 30)
            throw new BookingOverstayException();

        //The reservation should start at least the next day of booking
        diff = ChronoUnit.DAYS.between(LocalDate.now(), booking.getDateFrom());
        if (diff < 1)
            throw new EarlyBookingException();

        //The reservation should not overlap with an existing booking
        if (!isRoomAvailable(booking))
            throw new RoomAlreadyBookedException();
    }

    /**
     * Checks if the room is available for booking
     * @param booking the booking to be validated
     * @return true if room is available, false otherwise
     */
    private boolean isRoomAvailable(BookingDto booking) {
        return bookingRepo.findConflictBooking(booking.getDateFrom(), booking.getDateTo()).isEmpty();
    }
}
