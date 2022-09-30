package com.indocyber.booking.futsal.entity;

import com.indocyber.booking.futsal.dto.lapangan.LapanganGridDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lapangan")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Lapangan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer lapanganId;

    @Column(name = "NamaLapangan")
    private String namaLapangan;

    @Column(name = "JenisLapangan")
    private String jenisLapangan;

    public Lapangan(String namaLapangan, String jenisLapangan) {
        this.namaLapangan = namaLapangan;
        this.jenisLapangan = jenisLapangan;
    }
    public LapanganGridDTO convertToLapanganDTO(){
        return LapanganGridDTO.builder().idLapangan(this.lapanganId)
                .namaLapangan(this.namaLapangan)
                .jenisLapangan(this.jenisLapangan).build();
    }
}
