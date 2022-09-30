package com.indocyber.booking.futsal.service;

import com.indocyber.booking.futsal.dao.LapanganRepository;
import com.indocyber.booking.futsal.dto.lapangan.InsertLapanganDTO;
import com.indocyber.booking.futsal.dto.lapangan.LapanganGridDTO;
import com.indocyber.booking.futsal.entity.Lapangan;
import com.indocyber.booking.futsal.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LapanganServiceImpl implements LapanganService {
    @Autowired
    private LapanganRepository lapanganRepository;

    @Override
    public Lapangan insertLapangan(InsertLapanganDTO dto) {
        Lapangan lapangan = new Lapangan(dto.getNamaLapangan(),dto.getJenisLapangan());
        lapanganRepository.save(lapangan);
        return lapangan;
    }

    @Override
    public Page<LapanganGridDTO> getListLapangan(Integer page, String namaLapangan) {
        Pageable pageable = PageRequest.of(page-1,3, Sort.by("Id"));
        Page<Lapangan> lapangans = lapanganRepository.findAllLapangan(pageable,namaLapangan);

        Page<LapanganGridDTO> lapanganGridDTOS = new PageImpl<>(lapangans.stream().map(each -> each.convertToLapanganDTO())
                .collect(Collectors.toList()),lapangans.getPageable(),lapangans.getTotalElements());
        return lapanganGridDTOS;
    }

    @Override
    public LapanganGridDTO getLapanganById(Integer idLapangan) {
        Optional<Lapangan> optionalLapangan = lapanganRepository.findById(idLapangan);
        Lapangan lapangan = null;
        if (optionalLapangan.isPresent()){
            lapangan= optionalLapangan.get();
        }else {
            throw new NotFoundException("Id Not Found");
        }
        LapanganGridDTO lapanganGridDTO =new LapanganGridDTO(lapangan.getLapanganId(),lapangan.getNamaLapangan(),lapangan.getJenisLapangan());
        return lapanganGridDTO;
    }

    @Override
    public LapanganGridDTO updateLapangan(LapanganGridDTO dto) {
        Optional<Lapangan> optionalLapangan=lapanganRepository.findById(dto.getIdLapangan());
        Lapangan lapangan = null;
        if (optionalLapangan.isPresent()){
            lapangan=optionalLapangan.get();
            lapangan.setJenisLapangan(dto.getJenisLapangan());
            lapangan.setNamaLapangan(dto.getNamaLapangan());
            lapanganRepository.save(lapangan);
        }else {
            throw new NotFoundException("id Not Found");
        }

        LapanganGridDTO lapanganGridDTO =new LapanganGridDTO(lapangan.getLapanganId(),lapangan.getNamaLapangan(),lapangan.getJenisLapangan());
        return lapanganGridDTO;
    }

    @Override
    public void delete(Integer idLapangan) {
        if (lapanganRepository.findById(idLapangan).isPresent()){
        lapanganRepository.deleteById(idLapangan);}
        throw new NotFoundException("Id Not Found");
    }
}
