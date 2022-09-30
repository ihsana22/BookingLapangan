package com.indocyber.booking.futsal.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RequestTokenDTO {

    private String username;

    private String password;

    private String subject;

    private String secretKey;

    private String audience;


}
