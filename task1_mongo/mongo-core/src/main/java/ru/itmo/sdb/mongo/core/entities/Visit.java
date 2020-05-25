package ru.itmo.sdb.mongo.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Visit {
    @Id
    public Long id;

    public Long dormitory_id;
    public Date enter_date;
    public Date exit_date;

    public String visitorFirstName;
    public String visitorLastName;
    public String visitorPatronymic;
}
