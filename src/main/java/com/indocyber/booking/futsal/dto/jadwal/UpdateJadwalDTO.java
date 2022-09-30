package com.indocyber.booking.futsal.dto.jadwal;

import lombok.*;

import java.math.BigDecimal;

@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UpdateJadwalDTO {

    private Integer idJam;
    private BigDecimal hargaSewa;
}
