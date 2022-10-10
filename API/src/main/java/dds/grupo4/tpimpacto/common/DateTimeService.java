package dds.grupo4.tpimpacto.common;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateTimeService {
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
