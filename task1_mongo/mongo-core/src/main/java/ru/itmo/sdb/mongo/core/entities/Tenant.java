package ru.itmo.sdb.mongo.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Tenant {
    @Id
    public Integer id;

    public Boolean privileged;
    public Integer payment_amount;
    public String payment_type;
    public Integer person_id;
    public Integer room_id;
}
