package com.drobov.project.project_calendar.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="dates")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="dateof")
    private LocalDate dateof;
    @Column(name="starttime")
    private LocalTime starttime;
    @Column(name="endtime")
    private LocalTime endtime;
    @Column(name="workbool")
    private Boolean workbool;
    @Column(name="doname")
    private String doname;
    @Column(name="descrip")
    private String descrip;
    @Column(name="salary")
    private int salary;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
