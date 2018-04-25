package com.alternate.sample.controllers;

import com.alternate.sample.entities.SampleEntity;
import com.alternate.sample.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
 * base mappings and other common attributes can define here (eg: produces = MediaType.APPLICATION_JSON_VALUE)
 */
@RestController
@RequestMapping(value = "api/samples", produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleRestController {

    /*
     * service is autowired in constructor
     */
    private SampleService sampleService;

    @Autowired
    public SampleRestController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    /*
     * get mapping with no value default is "" (eg: /api/samples)
     */
    @GetMapping
    public ResponseEntity<Iterable<SampleEntity>> getAllSamples() {
        /*
         * response entity is a wrapper class that we can use to send http responses
         * when using ResponseEntity.ok it automatically set response status and other relevant attributes
         */
        return ResponseEntity.ok(sampleService.getAllSamples());
    }

    /*
     * no need to use value = "{id}"
     * when there are more than one attribute inside @GetMapping
     * @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
     */
    @GetMapping("{id}")
    public ResponseEntity<Optional<SampleEntity>> getSampleById(@PathVariable("id") Integer id) {
        /*
         * response entity is a wrapper class that we can use to send http responses
         * when using ResponseEntity.ok it automatically set response status and other relevant attributes
         */
        return ResponseEntity.ok(sampleService.getSampleById(id));
    }

    /*
     * get mapping with no value default is "" (eg: /api/samples)
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSample(@RequestBody SampleEntity sampleEntity) {
        /*
         * response entity is a wrapper class that we can use to send http responses
         * when using ResponseEntity.ok it automatically set response status and other relevant attributes
         * use ? as the wildcard character when defining ResponseEntity<?>
         */
        sampleService.addSample(sampleEntity);
        /*
         * when there is no response body we have to use build() to build response
         */
        return ResponseEntity.ok().build();
    }

}
