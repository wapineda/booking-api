package com.alten.bookingapi.repository;

import com.alten.bookingapi.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Bookings, Integer> {
    List<Bookings> findAllByCancelledFalseOrCancelledIsNull();

    @Query("SELECT b FROM Bookings b WHERE b.cancelled = false AND :date BETWEEN b.dateFrom AND b.dateTo")
    List<Bookings> findActiveBookings(@Param("date") LocalDate date);

    @Query("SELECT b FROM Bookings b WHERE b.cancelled = false AND ((:from BETWEEN b.dateFrom AND b.dateTo) OR (:to BETWEEN b.dateFrom AND b.dateTo))")
    List<Bookings> findConflictBooking(@Param("from") LocalDate from, @Param("to") LocalDate to);

}
