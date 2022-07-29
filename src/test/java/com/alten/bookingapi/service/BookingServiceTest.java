package com.alten.bookingapi.service;

import com.alten.bookingapi.dto.BookingDto;
import com.alten.bookingapi.dto.UserDto;
import com.alten.bookingapi.entity.Bookings;
import com.alten.bookingapi.entity.Users;
import com.alten.bookingapi.exception.*;
import com.alten.bookingapi.repository.BookingRepo;
import com.alten.bookingapi.repository.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.alten.bookingapi.util.Entities.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private BookingRepo bookingRepo;
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private BookingService service;

    @Test
    @DisplayName("All bookings in the database should be returned")
    void getAllBookings() {
        //Given
        List<Bookings> given = randomBooks();

        //When
        when(bookingRepo.findAll()).thenReturn(given);

        List<Bookings> expected = service.getAllBookings();

        //Then
        assertEquals(given, expected);
    }

    @Test
    @DisplayName("All users in the database should be returned")
    void getAllUsers() {
        //Given
        List<Users> given = randomUsers();

        //When
        when(userRepo.findAll()).thenReturn(given);

        List<Users> expected = service.getAllUsers();

        //Then
        assertEquals(given, expected);
    }

    @Test
    @DisplayName("A message with room availability should be returned")
    void checkRoomAvailability() {
        //Given
        List<Bookings> given = randomBooks();

        //When
        when(bookingRepo.findAllByCancelledFalseOrCancelledIsNull()).thenReturn(given);

        String message = service.checkRoomAvailability();

        //Then
        assertFalse(message.isEmpty());
    }

    @Test
    @DisplayName("A message with room availability should be returned")
    void checkRoomAvailability_2() {
        //When
        when(bookingRepo.findAllByCancelledFalseOrCancelledIsNull()).thenReturn(Collections.emptyList());

        String message = service.checkRoomAvailability();

        //Then
        assertEquals("Room is available for booking", message);
    }

    @Test
    @DisplayName("Current bookings should be returned")
    void getCurrentBookings() {
        //Given
        List<Bookings> given = randomBooks();

        //When
        when(bookingRepo.findActiveBookings(any(LocalDate.class))).thenReturn(given);

        List<Bookings> expected = service.getCurrentBookings();

        //Then
        assertEquals(given, expected);
    }

    @Test
    @DisplayName("Save method should be called and a booking should be created")
    void createBooking() {
        //Given
        BookingDto dto = BookingDto.builder()
                .dateFrom(LocalDate.now().plusDays(1))
                .dateTo(LocalDate.now().plusDays(2))
                .user(UserDto.builder()
                        .name("Alejandro")
                        .lastName("Pineda")
                        .email("one_email")
                        .build())
                .cancelled(false)
                .build();

        service.createBooking(dto);

        //Then
        verify(bookingRepo).save(any(Bookings.class));
    }

    @Test
    @DisplayName("MissingDatesException should be thrown")
    void createBooking_1() {
        //Given
        BookingDto dto = BookingDto.builder()
                .dateTo(null)
                .dateFrom(LocalDate.now().plusDays(2))
                .user(UserDto.builder()
                        .name("Alejandro")
                        .lastName("Pineda")
                        .email("one_email")
                        .build())
                .cancelled(false)
                .build();

        //Then
        assertThrows(
                MissingDatesException.class,
                () -> service.createBooking(dto)
        );
    }

    @Test
    @DisplayName("BookingDaysException should be thrown")
    void createBooking_2() {
        //Given
        BookingDto dto = BookingDto.builder()
                .dateFrom(LocalDate.now().plusDays(2))
                .dateTo(LocalDate.now().plusDays(10))
                .user(UserDto.builder()
                        .name("Alejandro")
                        .lastName("Pineda")
                        .email("one_email")
                        .build())
                .cancelled(false)
                .build();

        //Then
        assertThrows(
                BookingDaysException.class,
                () -> service.createBooking(dto)
        );
    }

    @Test
    @DisplayName("BookingOverstayException should be thrown")
    void createBooking_3() {
        //Given
        BookingDto dto = BookingDto.builder()
                .dateFrom(LocalDate.now().plusDays(40))
                .dateTo(LocalDate.now().plusDays(41))
                .user(UserDto.builder()
                        .name("Alejandro")
                        .lastName("Pineda")
                        .email("one_email")
                        .build())
                .cancelled(false)
                .build();

        //Then
        assertThrows(
                BookingOverstayException.class,
                () -> service.createBooking(dto)
        );
    }

    @Test
    @DisplayName("RoomAlreadyBookedException should be thrown")
    void createBooking_4() {
        //Given
        List<Bookings> given = List.of(randomBook());
        BookingDto dto = BookingDto.builder()
                .dateFrom(LocalDate.now().plusDays(2))
                .dateTo(LocalDate.now().plusDays(3))
                .user(UserDto.builder()
                        .name("Alejandro")
                        .lastName("Pineda")
                        .email("one_email")
                        .build())
                .cancelled(false)
                .build();
        given.get(0).setDateFrom(dto.getDateFrom());
        given.get(0).setDateTo(dto.getDateTo());

        //When
        when(bookingRepo.findConflictBooking(any(LocalDate.class), any(LocalDate.class))).thenReturn(given);

        //Then
        assertThrows(
                RoomAlreadyBookedException.class,
                () -> service.createBooking(dto)
        );
    }

    @Test
    @DisplayName("EarlyBookingException should be thrown")
    void createBooking_5() {
        //Given
        BookingDto dto = BookingDto.builder()
                .dateFrom(LocalDate.now())
                .dateTo(LocalDate.now().plusDays(1))
                .user(UserDto.builder()
                        .name("Alejandro")
                        .lastName("Pineda")
                        .email("one_email")
                        .build())
                .cancelled(false)
                .build();

        //Then
        assertThrows(
                EarlyBookingException.class,
                () -> service.createBooking(dto)
        );
    }

    @Test
    @DisplayName("DateRangeException should be thrown")
    void createBooking_6() {
        //Given
        BookingDto dto = BookingDto.builder()
                .dateFrom(LocalDate.now().plusDays(1))
                .dateTo(LocalDate.now())
                .user(UserDto.builder()
                        .name("Alejandro")
                        .lastName("Pineda")
                        .email("one_email")
                        .build())
                .cancelled(false)
                .build();

        //Then
        assertThrows(
                DateRangeException.class,
                () -> service.createBooking(dto)
        );
    }

    @Test
    @DisplayName("Save method should be called and a booking should be updated")
    void updateBooking() {
        Bookings existing = randomBook();
        //Given
        BookingDto dto = BookingDto.builder()
                .id(1)
                .cancelled(true)
                .build();

        //When
        when(bookingRepo.findById(any(Integer.class))).thenReturn(Optional.of(existing));

        service.updateBooking(dto);

        //Then
        verify(bookingRepo).save(any(Bookings.class));
    }

    @Test
    @DisplayName("Save method should be called and dates should be updated if cancelled is false")
    void updateBooking_2() {
        Bookings existing = randomBook();
        //Given
        BookingDto dto = BookingDto.builder()
                .id(1)
                .dateFrom(LocalDate.now().plusDays(2))
                .dateTo(LocalDate.now().plusDays(3))
                .cancelled(false)
                .build();

        //When
        when(bookingRepo.findById(any(Integer.class))).thenReturn(Optional.of(existing));

        service.updateBooking(dto);

        //Then
        verify(bookingRepo).save(any(Bookings.class));
    }

    @Test
    @DisplayName("Save method should be called and dates should not be updated if dto dates are null")
    void updateBooking_3() {
        Bookings existing = randomBook();
        existing.setDateFrom(LocalDate.now().plusDays(2));
        existing.setDateTo(LocalDate.now().plusDays(3));
        //Given
        BookingDto dto = BookingDto.builder()
                .id(1)
                .dateFrom(null)
                .dateTo(null)
                .cancelled(false)
                .build();

        //When
        when(bookingRepo.findById(any(Integer.class))).thenReturn(Optional.of(existing));

        service.updateBooking(dto);

        //Then
        verify(bookingRepo).save(any(Bookings.class));
    }

    @Test
    @DisplayName("Save method should be called when cancelling a booking")
    void cancelBooking() {
        //Given
        Bookings existing = randomBook();

        //When
        when(bookingRepo.findById(any(Integer.class))).thenReturn(Optional.of(existing));

        service.cancelBooking(1);

        //Then
        verify(bookingRepo).save(any(Bookings.class));
    }
}
