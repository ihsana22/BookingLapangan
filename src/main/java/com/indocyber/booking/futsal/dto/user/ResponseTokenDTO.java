package com.indocyber.booking.futsal.dto.user;


import lombok.*;

import javax.websocket.server.ServerEndpoint;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter @Getter
public class ResponseTokenDTO {

    private String username;

    private String role;

    private String token;


}