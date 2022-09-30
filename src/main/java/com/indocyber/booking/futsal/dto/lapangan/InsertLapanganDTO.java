package com.indocyber.booking.futsal.dto.lapangan;

import lombok.*;

@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)

public class InsertLapanganDTO {


    private String namaLapangan;

    private String jenisLapangan;
}
