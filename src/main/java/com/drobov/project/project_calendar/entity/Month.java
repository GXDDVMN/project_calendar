package com.drobov.project.project_calendar.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="month")
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="month")
    private YearMonth month;
    @Column(name="notes")
    private String notes;
}
