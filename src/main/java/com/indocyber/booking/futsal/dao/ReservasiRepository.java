package com.indocyber.booking.futsal.dao;

import com.indocyber.booking.futsal.entity.Reservasi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ReservasiRepository extends JpaRepository<Reservasi, Integer> {
    @Query(value = """
            SELECT * FROM Reservasi res WHERE res.NamaPelanggan LIKE %:namaPelanggan%
            """,nativeQuery = true)
    Page<Reservasi> getAllReservasi(Pageable pageable, String namaPelanggan);
}