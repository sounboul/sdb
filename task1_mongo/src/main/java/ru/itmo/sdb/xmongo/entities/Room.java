package ru.itmo.sdb.xmongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
public class Room {
    @Id
    public Integer id;

    public Integer bugs;
    public Integer capacity;
    public Date disinfection_date;
    public Integer dormitory_id;
    public String number;
}
