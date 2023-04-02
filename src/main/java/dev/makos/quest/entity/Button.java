package dev.makos.quest.entity;

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
@Table(name = "buttons")
public class Button {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "button_id")
    private Long id;

    @Column(name = "main_description")
    private String mainDescription;

    @Column(name = "alt_description")
    private String altDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_next_level_id")
    private Level mainLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alt_next_level_id")
    private Level altLevel;

    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private Requirement requirement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Button button = (Button) o;
        return getId() != null && Objects.equals(getId(), button.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
