package com.alternate.sample.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SampleSubEntity {
    @Id
    private int id;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
