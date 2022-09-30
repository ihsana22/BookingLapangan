package com.indocyber.booking.futsal.service;

import com.indocyber.booking.futsal.dto.reservasi.InsertReservasiDTO;
import com.indocyber.booking.futsal.dto.reservasi.ReservasiGridDTO;
import com.indocyber.booking.futsal.entity.Reservasi;
import org.springframework.data.domain.Page;

public interface ReservasiService {
    Reservasi insertReservasi(InsertReservasiDTO dto, String username);

    Page<ReservasiGridDTO> getAllReservasi(String username, Integer page, String namaPelanggan);

    Reservasi confirmReservasi(Integer idReservasi);

    Reservasi cancelReservasi(Integer idReservasi);
}
