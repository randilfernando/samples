package com.alternate.sample.services;


import com.alternate.sample.entities.SampleEntity;

import java.util.Optional;

/*
 * normally we use interface to abstract implementation
 * useful when using dependency injection
 * no need to depend on concrete implementation
 */
public interface SampleService {
    void addSample(SampleEntity sampleEntity);
    Iterable<SampleEntity> getAllSamples();
    Optional<SampleEntity> getSampleById(Integer id);
}
