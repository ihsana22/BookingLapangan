package com.indocyber.booking.futsal.dto.jadwal;

import com.indocyber.booking.futsal.entity.Lapangan;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter @Getter
public class InsertJadwalDTO {

    @DateTimeFormat(pattern = "hh:m")
    private LocalTime jamMulai ;

    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime jamSelesai;

    private BigDecimal hargaSewa;

    private Integer idLapangan;
}
