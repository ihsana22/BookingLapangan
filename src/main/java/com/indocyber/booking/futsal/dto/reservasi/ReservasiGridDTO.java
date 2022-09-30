package com.indocyber.booking.futsal.dto.reservasi;

import com.indocyber.booking.futsal.entity.Jadwal;
import com.indocyber.booking.futsal.entity.Users;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter @Getter
@Builder
public class ReservasiGridDTO {

    private String namaPelanggan;

    private String noHpPelanggan;

    private String hari;

    private String tanggal;

    private String jamMulai;

    private String jamSelesai;

    private String bill;

    private String username;

}
