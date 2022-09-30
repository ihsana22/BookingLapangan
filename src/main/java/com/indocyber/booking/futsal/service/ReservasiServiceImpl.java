package com.indocyber.booking.futsal.service;

import com.indocyber.booking.futsal.dao.JadwalRepository;
import com.indocyber.booking.futsal.dao.ReservasiRepository;
import com.indocyber.booking.futsal.dao.UserRepository;
import com.indocyber.booking.futsal.dto.reservasi.InsertReservasiDTO;
import com.indocyber.booking.futsal.dto.reservasi.ReservasiGridDTO;
import com.indocyber.booking.futsal.dto.utility.Helper;
import com.indocyber.booking.futsal.entity.Jadwal;
import com.indocyber.booking.futsal.entity.Reservasi;
import com.indocyber.booking.futsal.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservasiServiceImpl implements ReservasiService{

    @Autowired
    private ReservasiRepository reservasiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JadwalRepository jadwalRepository;

    @Override
    public Reservasi insertReservasi(InsertReservasiDTO dto, String username) {
        Optional<Users> optUser = userRepository.findById(username);
        Users user = null;
        if (optUser.isPresent()){
            user= optUser.get();
        }
        Optional<Jadwal> optJadwal=jadwalRepository.findById(dto.getIdJam());
        Jadwal jadwal = null;
        if (optJadwal.isPresent()){
            jadwal= optJadwal.get();
        }
        Reservasi reservasi = new Reservasi(dto.getNamaPelanggan(),dto.getNoHpPelanggan(),dto.getHari(),dto.getTanggal(),jadwal,"Pending",user);
        reservasiRepository.save(reservasi);
        return reservasi;
    }

    @Override
    public Page<ReservasiGridDTO> getAllReservasi(String username, Integer page, String namaPelanggan) {
        Pageable pageable = PageRequest.of(page-1,3, Sort.by("IdReservasi"));
        Page<Reservasi> reservasiPage = reservasiRepository.getAllReservasi(pageable,namaPelanggan);
        Page<ReservasiGridDTO> pageObjectReservasi = new PageImpl<>(reservasiPage.stream().map(each -> each.convertToReservasiDTO())
                .collect(Collectors.toList()),reservasiPage.getPageable(),reservasiPage.getTotalElements());
        return pageObjectReservasi;
    }

    @Override
    public Reservasi confirmReservasi(Integer idReservasi) {
        Optional<Reservasi> optReservasi = reservasiRepository.findById(idReservasi);
        Reservasi reservasi = null;
        if (optReservasi.isPresent()){
            reservasi=optReservasi.get();
        }
        reservasi.setStatusReservasi("Confirm");
        reservasiRepository.save(reservasi);
        return reservasi;
    }

    @Override
    public Reservasi cancelReservasi(Integer idReservasi) {
        Optional<Reservasi> reservasiOptional = reservasiRepository.findById(idReservasi);
        Reservasi reservasi = null;
        if (reservasiOptional.isPresent()){
            reservasi=reservasiOptional.get();
        }
        reservasi.setStatusReservasi("Cancel");
        reservasiRepository.save(reservasi);
        return reservasi;
    }
}
