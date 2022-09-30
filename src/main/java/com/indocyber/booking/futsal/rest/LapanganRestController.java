package com.indocyber.booking.futsal.rest;

import com.indocyber.booking.futsal.dto.lapangan.InsertLapanganDTO;
import com.indocyber.booking.futsal.dto.lapangan.LapanganGridDTO;
import com.indocyber.booking.futsal.entity.Lapangan;
import com.indocyber.booking.futsal.service.LapanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/lapangan")
public class LapanganRestController {

    @Autowired
    private LapanganService lapanganService;

    @PostMapping("/insert")
    public ResponseEntity<Object> insertLapangan (@Valid @RequestBody InsertLapanganDTO dto){
        Lapangan lapangan = lapanganService.insertLapangan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(lapangan);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getListLapangan(@RequestParam(defaultValue = "1")Integer page,
                                                  @RequestParam(defaultValue = "")String namaLapangan){
        Page<LapanganGridDTO> lapanganGridDTOPage = lapanganService.getListLapangan(page,namaLapangan);

        return ResponseEntity.status(HttpStatus.OK).body(lapanganGridDTOPage);
    }

    @GetMapping("/{idLapangan}")
    public ResponseEntity<Object> getLapanganById(@PathVariable Integer idLapangan){
        LapanganGridDTO lapanganGridDTO = lapanganService.getLapanganById(idLapangan);
        return ResponseEntity.status(HttpStatus.OK).body(lapanganGridDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateLapangan(@RequestBody LapanganGridDTO dto){
        LapanganGridDTO lapanganGridDTO = lapanganService.updateLapangan(dto);
        return ResponseEntity.status(HttpStatus.OK).body(lapanganGridDTO);
    }

    @DeleteMapping("/{idLapangan}")
    public ResponseEntity<Object> delete(@PathVariable Integer idLapangan){
        lapanganService.delete(idLapangan);
        return ResponseEntity.status(HttpStatus.OK).body("Lapangan dengan Id "+idLapangan+" Berhasil terhapus");
    }
}
