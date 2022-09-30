package com.indocyber.booking.futsal.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter @Getter
public class ErrorResponse {

    private int status;

    private Object message;

    private long timestamp;


}
