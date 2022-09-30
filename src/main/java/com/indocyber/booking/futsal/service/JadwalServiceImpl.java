package com.indocyber.booking.futsal.service;

import com.indocyber.booking.futsal.dao.JadwalRepository;
import com.indocyber.booking.futsal.dao.LapanganRepository;
import com.indocyber.booking.futsal.dto.jadwal.InsertJadwalDTO;
import com.indocyber.booking.futsal.dto.jadwal.JadwalGridDTO;
import com.indocyber.booking.futsal.dto.jadwal.UpdateJadwalDTO;
import com.indocyber.booking.futsal.dto.utility.Helper;
import com.indocyber.booking.futsal.entity.Jadwal;
import com.indocyber.booking.futsal.entity.Lapangan;
import com.indocyber.booking.futsal.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JadwalServiceImpl implements JadwalService {

    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private LapanganRepository lapanganRepository;

    @Override
    public Jadwal insertJadwal(InsertJadwalDTO dto) {

        Optional<Lapangan> optionalLapangan = lapanganRepository.findById(dto.getIdLapangan());
        Lapangan lapangan = null;
        if (optionalLapangan.isPresent()){
            lapangan= optionalLapangan.get();
        }
        Jadwal jadwal = new Jadwal(dto.getJamMulai(),dto.getJamSelesai(),dto.getHargaSewa(),lapangan);
        jadwalRepository.save(jadwal);
        return jadwal;
    }

    @Override
    public Page<Jadwal> getAllJadwal(Pageable pageable) {
        Page<Jadwal> jadwalPage = jadwalRepository.findAllJadwal(pageable);
        return jadwalPage;
    }

    @Override
    public JadwalGridDTO getJadwalById(Integer idJam) {
       Optional<Jadwal> optionalJadwal = jadwalRepository.findById(idJam);
        Jadwal jadwal = null;
        if (optionalJadwal.isPresent()){
            jadwal=optionalJadwal.get();
        }else {
            throw new  NotFoundException("Id Not Found");
        }
        JadwalGridDTO jadwalGridDTO = new JadwalGridDTO(jadwal.getIdJam(), Helper.formatWaktu(jadwal.getJamMulai()),Helper.formatWaktu(jadwal.getJamSelesai()),Helper.formatRupiah(jadwal.getHargaSewa()));
        return jadwalGridDTO;
    }

    @Override
    public JadwalGridDTO updateJadwal(UpdateJadwalDTO dto) {
        Optional<Jadwal> optJadwal = jadwalRepository.findById(dto.getIdJam());

        Jadwal jadwal = null;
        if (optJadwal.isPresent()){
            jadwal=optJadwal.get();
            jadwal.setHargaSewa(dto.getHargaSewa());
            jadwalRepository.save(jadwal);
        }else {
            throw new NotFoundException("Id Not Found");
        }
        JadwalGridDTO jadwalGridDTO = new JadwalGridDTO(jadwal.getIdJam(),Helper.formatWaktu(jadwal.getJamMulai()),Helper.formatWaktu(jadwal.getJamSelesai()),Helper.formatRupiah(jadwal.getHargaSewa()));
        return jadwalGridDTO;
    }

    @Override
    public void delete(Integer idJam) {
        if (jadwalRepository.findById(idJam).isPresent()){
            jadwalRepository.deleteById(idJam);
        }
        throw new NotFoundException("Id Not Found");
    }
}
