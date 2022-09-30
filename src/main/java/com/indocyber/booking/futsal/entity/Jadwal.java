package com.indocyber.booking.futsal.entity;

import com.indocyber.booking.futsal.dto.jadwal.JadwalGridDTO;
import com.indocyber.booking.futsal.dto.utility.Helper;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Jadwal")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter @Getter
@Builder
public class Jadwal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdJam")
    private Integer idJam;

    @Column(name = "JamMulai")
    private LocalTime jamMulai ;

    @Column(name = "JamSelesai")
    private LocalTime jamSelesai;

    @Column(name = "HargaSewa")
    private BigDecimal hargaSewa;

    @ManyToOne
    @JoinColumn(name = "IdLapangan")
    private Lapangan lapangan;

    @OneToMany(mappedBy = "jadwal")
    private List<Reservasi> reservasis;

    public Jadwal(LocalTime jamMulai, LocalTime jamSelesai, BigDecimal hargaSewa, Lapangan lapangan) {
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.hargaSewa = hargaSewa;
        this.lapangan = lapangan;
    }
    public JadwalGridDTO convertToDTO(){
        return JadwalGridDTO.builder().
                 idJam(this.getIdJam())
                .jamMulai(Helper.formatWaktu(this.jamMulai))
                .jamSelesai(Helper.formatWaktu(this.jamSelesai))
                .hargaSewa(Helper.formatRupiah(this.hargaSewa)).build();
    }
}
