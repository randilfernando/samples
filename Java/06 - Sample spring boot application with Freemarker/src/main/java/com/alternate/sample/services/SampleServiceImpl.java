package com.alternate.sample.services;

import com.alternate.sample.entities.SampleEntity;
import com.alternate.sample.repositories.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SampleServiceImpl implements SampleService {
    private SampleRepository sampleRepository;

    @Autowired
    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public void addSample(SampleEntity sampleEntity) {
        sampleRepository.save(sampleEntity);
    }

    @Override
    public Iterable<SampleEntity> getAllSamples() {
        return sampleRepository.findAll();
    }

    @Override
    public Optional<SampleEntity> getSampleById(Integer id) {
        return sampleRepository.findById(id);
    }
}
