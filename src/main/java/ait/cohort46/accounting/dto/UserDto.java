package ait.cohort46.accounting.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private String login;
    private String firstName;
    private String lastName;
    @Singular
    private Set<String> roles;
}
