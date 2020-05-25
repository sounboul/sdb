package ru.itmo.sdb.mongo.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Room {
    @Id
    public Long id;

    public Boolean bugs;
    public Integer capacity;
    public Date disinfection_date;
    public Long dormitory_id;
    public String number;
}
