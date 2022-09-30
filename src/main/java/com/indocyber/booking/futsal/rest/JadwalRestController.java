package com.indocyber.booking.futsal.rest;

import com.indocyber.booking.futsal.dto.jadwal.InsertJadwalDTO;
import com.indocyber.booking.futsal.dto.jadwal.JadwalGridDTO;
import com.indocyber.booking.futsal.dto.jadwal.UpdateJadwalDTO;
import com.indocyber.booking.futsal.entity.Jadwal;
import com.indocyber.booking.futsal.service.JadwalService;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jadwal")
public class JadwalRestController {

    @Autowired
    private JadwalService service;

    @PostMapping("/insert")
    public ResponseEntity<Object> insertJadwal(@Valid @RequestBody InsertJadwalDTO dto){
        Jadwal jadwal = service.insertJadwal(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(jadwal);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<JadwalGridDTO>> listJadwal(@RequestParam(defaultValue = "1")Integer page){
        Pageable pageable = PageRequest.of(page-1,3, Sort.by("IdJam"));
        Page<Jadwal> jadwal = service.getAllJadwal(pageable);

        Page<JadwalGridDTO> jadwalGridDTO = new PageImpl<>(jadwal.stream().map(each -> each.convertToDTO())
                .collect(Collectors.toList()),jadwal.getPageable(),jadwal.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(jadwalGridDTO);
    }
    @GetMapping("/{idJam}")
    public ResponseEntity<Object> getJadwalById(@PathVariable Integer idJam){
        JadwalGridDTO jadwalGridDTO = service.getJadwalById(idJam);
        return ResponseEntity.status(HttpStatus.OK).body(jadwalGridDTO);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateJadwal(@Valid @RequestBody UpdateJadwalDTO dto){
        JadwalGridDTO jadwalGridDTO = service.updateJadwal(dto);
        return ResponseEntity.status(HttpStatus.OK).body(jadwalGridDTO);
    }

    @DeleteMapping("/{idJam}")
    public ResponseEntity<Object> deleteJadwal(@PathVariable Integer idJam){
        service.delete(idJam);
        return ResponseEntity.status(HttpStatus.OK).body("Jadwal dengen id "+idJam+" berhasil terhapuus");
    }
}
