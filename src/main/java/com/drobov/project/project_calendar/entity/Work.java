package com.drobov.project.project_calendar.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="works")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="name")
    private String name;
    @Column(name="starttime")
    private LocalTime starttime;
    @Column(name="endtime")
    private LocalTime endtime;
    @Column(name="salary")
    private double salary;
    @Column(name="color")
    private String color;
    @Column(name="work_schedule")
    private String schedule;
    @Column(name = "start_day")
    private String start_day;
}
