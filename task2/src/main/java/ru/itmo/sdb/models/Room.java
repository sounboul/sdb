package ru.itmo.sdb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class Room implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dormitory_id", referencedColumnName = "id")
    private Dormitory dormitory;

    private int number;

    private int capacity;

    private boolean bugs;

    private Date disinfectionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isBugs() {
        return bugs;
    }

    public void setBugs(boolean bugs) {
        this.bugs = bugs;
    }

    public Date getDisinfectionDate() {
        return disinfectionDate;
    }

    public void setDisinfectionDate(Date disinfectionDate) {
        this.disinfectionDate = disinfectionDate;
    }
}
