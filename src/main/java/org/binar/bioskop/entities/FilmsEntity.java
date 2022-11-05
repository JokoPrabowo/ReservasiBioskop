package org.binar.bioskop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "films")
public class FilmsEntity implements Serializable{
    
    @NotNull
    @Id
    @Column(name = "film_code")
    private String filmCode;

    @NotNull
    @Column(name = "film_name")
    private String filmName;

    @NotNull
    @Column(name = "category")
    private String category;
    
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "onShow", columnDefinition = "DATE")
    private LocalDate onShow;
}
