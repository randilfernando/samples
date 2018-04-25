package com.alternate.sample.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;


@Entity
public class SampleEntity {
    @Id
    private int id;
    private String data;
    @OneToMany
    private Collection<SampleSubEntity> sampleSubEntities;

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

    public Collection<SampleSubEntity> getSampleSubEntities() {
        return sampleSubEntities;
    }

    public void setSampleSubEntities(Collection<SampleSubEntity> sampleSubEntities) {
        this.sampleSubEntities = sampleSubEntities;
    }
}
