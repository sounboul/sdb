package ru.itmo.sdb.xmongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
public class Visit {
    @Id
    public Integer id;

    public Integer dormitory_id;
    public Date enter_date;
    public Date exit_date;
    public Integer person_id;
}
