package org.binar.SpringJPA.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class SchedulesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(name = "film_code")
    private String filmCode;

    @ManyToOne
    @JoinColumn(name = "film_code", referencedColumnName = "film_code", insertable = false, updatable = false)
    private FilmsEntity filmsEntity;

    @Column(name = "studio_id")
    private Integer studioId;

    @OneToOne
    @JoinColumn(name = "studio_id", insertable = false, updatable = false)
    private StudiosEntity studiosEntity;

    @Column(name = "price")
    private Integer price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "show_date")
    private LocalDate showDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "start_at")
    private LocalTime startAt;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "end_at")
    private LocalTime endAt;
}
