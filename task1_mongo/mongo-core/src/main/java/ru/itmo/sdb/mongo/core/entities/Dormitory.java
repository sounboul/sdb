package ru.itmo.sdb.mongo.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Dormitory {
    @Id
    public Long id;

    public String city;
    public String address;
    public String name;
}
