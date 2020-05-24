package ru.itmo.sdb.mongo.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Dormitory {
    @Id
    public Integer id;

    public String city;
    public String address;
    public String name;
}
