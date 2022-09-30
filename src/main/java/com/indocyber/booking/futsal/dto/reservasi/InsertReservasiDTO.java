package com.indocyber.booking.futsal.dto.reservasi;

import com.indocyber.booking.futsal.entity.Jadwal;
import com.indocyber.booking.futsal.entity.Users;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter @Getter
public class InsertReservasiDTO {

    private String namaPelanggan;

    private String noHpPelanggan;

    private String hari;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal;

    private Integer idJam;

}
