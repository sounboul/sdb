package ru.itmo.sdb.mongo.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
public class Penalty {
    @Id
    public Integer id;

    public Integer dormitory_id;
    public String reason;
    public Date report_date;
    public Integer tenant_id;
}
