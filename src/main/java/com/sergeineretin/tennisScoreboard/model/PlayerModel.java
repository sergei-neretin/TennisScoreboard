package com.sergeineretin.tennisScoreboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Players")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "Name", nullable = false, unique = true)
    private String name;
}
