package com.indocyber.booking.futsal.rest;

import com.indocyber.booking.futsal.dto.reservasi.InsertReservasiDTO;
import com.indocyber.booking.futsal.dto.reservasi.ReservasiGridDTO;
import com.indocyber.booking.futsal.entity.Reservasi;
import com.indocyber.booking.futsal.service.ReservasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reservasi")
public class ReservasiRestController {

    @Autowired
    private ReservasiService service;


    @PostMapping("/insert")
    public ResponseEntity<Object> insertReservasi(@Valid @RequestBody InsertReservasiDTO dto, Authentication authentication){
        String username = authentication.getName();
        Reservasi reservasi = service.insertReservasi(dto,username);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservasi);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getListReservasi(@RequestParam(defaultValue = "1")Integer page,
                                                   @RequestParam(defaultValue = "")String namaPelanggan,
                                                   Authentication authentication){
        String username = authentication.getName();
        Page<ReservasiGridDTO> pageObjectReservasi = service.getAllReservasi(username,page,namaPelanggan);
        return ResponseEntity.status(HttpStatus.OK).body(pageObjectReservasi);
    }

    @PutMapping("/confirm/{idReservasi}")
    public ResponseEntity<Object> confirmReservasion(@PathVariable Integer idReservasi){
        Reservasi reservasi = service.confirmReservasi(idReservasi);
        return ResponseEntity.status(HttpStatus.OK).body(reservasi);
    }

    @PutMapping("/cancel/{idReservasi}")
    public ResponseEntity<Object> cancelReservasi(@PathVariable Integer idReservasi){
        Reservasi reservasi = service.cancelReservasi(idReservasi);
        return ResponseEntity.status(HttpStatus.OK).body(reservasi);
    }
    

}
