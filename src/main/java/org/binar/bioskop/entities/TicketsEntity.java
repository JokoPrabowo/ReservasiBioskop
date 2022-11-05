package org.binar.bioskop.entities;

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
@Table(name = "tickets")
public class TicketsEntity {

    @Null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @NotNull
    @Column(name = "username")
    private String username;

    @Null
    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private UsersEntity usersEntity;

    @NotNull
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Null
    @ManyToOne
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    private SchedulesEntity schedulesEntity;

    @NotNull
    @Column(name = "seat_row")
    private Character seatRow;

    @NotNull
    @Column(name = "seat_number")
    private Integer seatNumber;

}
