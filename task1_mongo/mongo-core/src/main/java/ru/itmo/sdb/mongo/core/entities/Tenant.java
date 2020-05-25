package ru.itmo.sdb.mongo.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Tenant {
    @Id
    public Long id;

    public Integer payment_amount;
    public String payment_type;
    public Long room_id;

    public String firstName;
    public String lastName;
    public String patronymic;
}
