package com.example.Asteroids.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class TwoDatesLocalDate {
    private LocalDate from;
    private LocalDate to;

    public long getDayDifference(){
         return ChronoUnit.DAYS.between(from, to);
    }

    public TwoDatesLocalDate(String from, String to){
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }
}
