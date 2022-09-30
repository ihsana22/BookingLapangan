package com.indocyber.booking.futsal.dto.lapangan;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class LapanganGridDTO {
    private Integer idLapangan;

    private String namaLapangan;

    private String jenisLapangan;
}
