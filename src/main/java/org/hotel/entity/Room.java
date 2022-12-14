package org.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    private String name;

    @Column(nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

}
