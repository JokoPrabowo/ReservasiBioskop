package org.binar.bioskop.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmModel {

    private String filmCode;
    private String filmName;
    private String category;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate onShow;
}
