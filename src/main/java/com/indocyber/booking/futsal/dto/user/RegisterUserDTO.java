package com.indocyber.booking.futsal.dto.user;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter @Getter
public class RegisterUserDTO {

    private String username;

    private String password;

    private String passwordConfirmation;

    private String name;
}
