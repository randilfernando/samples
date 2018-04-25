package com.alternate.sample.repositories;

import com.alternate.sample.entities.SampleEntity;
import org.springframework.data.repository.CrudRepository;

public interface SampleRepository extends CrudRepository<SampleEntity, Integer> {
}
