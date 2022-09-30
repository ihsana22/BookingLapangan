package com.indocyber.booking.futsal.dao;

import com.indocyber.booking.futsal.entity.Lapangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface LapanganRepository extends JpaRepository<Lapangan, Integer> {
    @Query(value = """
            SELECT * FROM Lapangan where NamaLapangan LIKE %:namaLapangan%
            """,nativeQuery = true)
    Page<Lapangan> findAllLapangan(Pageable pageable, String namaLapangan);
}