package com.indocyber.booking.futsal.entity;

import com.indocyber.booking.futsal.dto.reservasi.ReservasiGridDTO;
import com.indocyber.booking.futsal.dto.utility.Helper;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Reservasi")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Reservasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReservasi")
    private Integer idReservasi;

    @Column(name = "NamaPelanggan")
    private String namaPelanggan;

    @Column(name = "NoHpPelanggan")
    private String noHpPelanggan;

    @Column(name = "hari")
    private String hari;

    @Column(name = "Tangggal")
    private LocalDate tanggal;

    @Column(name = "StatusReservasi")
    private String statusReservasi;

    @ManyToOne
    @JoinColumn(name = "IdJam")
    private Jadwal jadwal;

    @ManyToOne
    @JoinColumn(name = "Username",updatable = false)
    private Users users;

    public Reservasi(String namaPelanggan, String noHpPelanggan, String hari, LocalDate tanggal, Jadwal jadwal,String statusReservasi, Users users) {
        this.namaPelanggan = namaPelanggan;
        this.noHpPelanggan = noHpPelanggan;
        this.hari = hari;
        this.tanggal = tanggal;
        this.jadwal = jadwal;
        this.statusReservasi=statusReservasi;
        this.users = users;
    }
    public ReservasiGridDTO convertToReservasiDTO(){
        return ReservasiGridDTO.builder()
                .namaPelanggan(this.namaPelanggan)
                .noHpPelanggan(this.noHpPelanggan)
                .hari(this.hari)
                .tanggal(Helper.formatTanggalIndonesia(this.tanggal))
                .jamMulai(Helper.formatWaktu(this.jadwal.getJamMulai()))
                .jamSelesai(Helper.formatWaktu(this.jadwal.getJamSelesai()))
                .username(this.users.getUsername()).build();
    }
}
