package com.indocyber.booking.futsal.dto.jadwal;

import lombok.*;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter @Getter
@Builder
public class JadwalGridDTO {
    private Integer idJam;

    private String jamMulai ;

    private String jamSelesai;

    private String hargaSewa;



}
