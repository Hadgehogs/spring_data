package org.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @JoinColumn(name = "room_name",nullable = false)
    @NonNull
    @ManyToOne
    private Room room;

    @Column(nullable = false)
    @NonNull
    private LocalDate beginDate;

    @Column(nullable = false)
    @NonNull
    private LocalDate endDate;

    @JoinColumn(name = "client_name",nullable = false)
    @NonNull
    @ManyToOne
    private Client client;

}
