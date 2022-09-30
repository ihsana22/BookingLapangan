package com.indocyber.booking.futsal.service;

import com.indocyber.booking.futsal.dto.lapangan.InsertLapanganDTO;
import com.indocyber.booking.futsal.dto.lapangan.LapanganGridDTO;
import com.indocyber.booking.futsal.entity.Lapangan;
import org.springframework.data.domain.Page;

public interface LapanganService {
    Lapangan insertLapangan(InsertLapanganDTO dto);

    Page<LapanganGridDTO> getListLapangan(Integer page, String namaLapangan);

    LapanganGridDTO getLapanganById(Integer idLapangan);

    LapanganGridDTO updateLapangan(LapanganGridDTO dto);

    void delete(Integer idLapangan);
}
