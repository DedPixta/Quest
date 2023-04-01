package dev.makos.game.tombofmagic.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Getter
@Setter

@Entity
@Table(name = "levels")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    @Column(length = 32)
    private String name;

    private String image;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_status")
    private GameStatus gameStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Level level = (Level) o;
        return getId() != null && Objects.equals(getId(), level.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
