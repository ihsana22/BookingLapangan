package com.indocyber.booking.futsal.service;

import com.indocyber.booking.futsal.dto.jadwal.InsertJadwalDTO;
import com.indocyber.booking.futsal.dto.jadwal.JadwalGridDTO;
import com.indocyber.booking.futsal.dto.jadwal.UpdateJadwalDTO;
import com.indocyber.booking.futsal.entity.Jadwal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JadwalService {
    Jadwal insertJadwal(InsertJadwalDTO dto);
    Page<Jadwal> getAllJadwal(Pageable pageable);

    JadwalGridDTO getJadwalById(Integer idJam);

    JadwalGridDTO updateJadwal(UpdateJadwalDTO dto);

    void delete(Integer idJam);
}
