package dev.makos.quest.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Getter
@Setter
@ToString

@Entity
@Table(name = "requirements")
public class Requirement {

    @Id
    @Column(name = "requirement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private String name;

    @Column(name = "game_id")
    private Long game;

}
