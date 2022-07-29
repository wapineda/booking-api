package com.alten.bookingapi.controller;

import com.alten.bookingapi.dto.BookingDto;
import com.alten.bookingapi.entity.Bookings;
import com.alten.bookingapi.entity.Users;
import com.alten.bookingapi.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.alten.bookingapi.util.Entities.randomBooks;
import static com.alten.bookingapi.util.Entities.randomUsers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookingControllerTest {
    private final BookingService service = mock(BookingService.class);

    @Test
    void getAllBookings() {
        //Given
        List<Bookings> expected = randomBooks();
        BookingController controller = new BookingController(service);
        //When
        when(service.getAllBookings()).thenReturn(expected);

        ResponseEntity<List<Bookings>> actual = controller.getAllBookings();
        //Then
        assertThat(actual).satisfies(
                response -> {
                    assertThat(response.getStatusCodeValue()).isEqualTo(200);
                    assertThat(response.getBody()).isSameAs(expected);
                }
        );
    }

    @Test
    void checkRoomAvailability() {
        //Given
        BookingController controller = new BookingController(service);
        //When
        when(service.checkRoomAvailability()).thenReturn("Room is available for booking");

        ResponseEntity<String> actual = controller.checkRoomAvailability();
        //Then
        assertThat(actual).satisfies(
                response -> {
                    assertThat(response.getStatusCodeValue()).isEqualTo(200);
                    assertThat(response.getBody()).isSameAs("Room is available for booking");
                }
        );
    }

    @Test
    void getCurrentBookings() {
        //Given
        List<Bookings> expected = randomBooks();
        BookingController controller = new BookingController(service);
        //When
        when(service.getCurrentBookings()).thenReturn(expected);

        ResponseEntity<List<Bookings>> actual = controller.getCurrentBookings();
        //Then
        assertThat(actual).satisfies(
                response -> {
                    assertThat(response.getStatusCodeValue()).isEqualTo(200);
                    assertThat(response.getBody()).isSameAs(expected);
                }
        );
    }

    @Test
    void getAllUsers() {
        //Given
        List<Users> expected = randomUsers();
        BookingController controller = new BookingController(service);
        //When
        when(service.getAllUsers()).thenReturn(expected);

        ResponseEntity<List<Users>> actual = controller.getAllUsers();
        //Then
        assertThat(actual).satisfies(
                response -> {
                    assertThat(response.getStatusCodeValue()).isEqualTo(200);
                    assertThat(response.getBody()).isSameAs(expected);
                }
        );
    }

    @Test
    void createBooking() {
        //Given
        BookingDto expected = BookingDto.builder().build();
        BookingController controller = new BookingController(service);


        ResponseEntity<String> actual = controller.createBooking(expected);

        //Then
        assertThat(actual).satisfies(
                response -> {
                    assertThat(response.getStatusCodeValue()).isEqualTo(200);
                    assertThat(response.getBody()).isSameAs("Booking created");
                }
        );
    }

    @Test
    void updateBooking() {
        //Given
        BookingDto expected = BookingDto.builder().build();
        BookingController controller = new BookingController(service);


        ResponseEntity<String> actual = controller.updateBooking(expected);

        //Then
        assertThat(actual).satisfies(
                response -> {
                    assertThat(response.getStatusCodeValue()).isEqualTo(200);
                    assertThat(response.getBody()).isSameAs("Booking updated");
                }
        );
    }

    @Test
    void cancelBooking() {
        //Given
        BookingController controller = new BookingController(service);

        ResponseEntity<String> actual = controller.cancelBooking(1);

        //Then
        assertThat(actual).satisfies(
                response -> {
                    assertThat(response.getStatusCodeValue()).isEqualTo(200);
                    assertThat(response.getBody()).isSameAs("Booking cancelled");
                }
        );
    }
}
