package com.indocyber.booking.futsal.dao;

import com.indocyber.booking.futsal.entity.Jadwal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JadwalRepository extends JpaRepository<Jadwal, Integer> {

    @Query(value = """
            select * from Jadwal
            """,nativeQuery = true)
    Page<Jadwal> findAllJadwal(Pageable pageable);
}