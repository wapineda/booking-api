package com.alten.bookingapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Bookings")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Boolean cancelled;
}
