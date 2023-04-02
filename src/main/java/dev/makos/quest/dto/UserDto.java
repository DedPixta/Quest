package dev.makos.quest.dto;

import dev.makos.quest.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String login;
    private Role role;
    List<Long> gamesIdWithSession;

}
