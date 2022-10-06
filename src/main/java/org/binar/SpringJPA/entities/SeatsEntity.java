package org.binar.SpringJPA.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
public class SeatsEntity {

    @EmbeddedId
    private SeatId seatId;

    @NotNull
    @Column(name = "studio_id")
    private Integer studioId;

    @Null
    @ManyToOne
    @JoinColumn(name = "studio_id", insertable = false, updatable = false)
    private StudiosEntity studiosEntity;

    @Null
    @Column(name = "seat_status")
    private boolean seatStatus;
}
