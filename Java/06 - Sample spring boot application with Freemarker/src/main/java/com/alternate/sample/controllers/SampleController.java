package com.alternate.sample.controllers;

import com.alternate.sample.entities.SampleEntity;
import com.alternate.sample.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * render views using freemarker template engine
 */

@Controller
public class SampleController {

    /*
     * service is autowired in constructor
     */
    private SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    /*
     * get data and render view using data retrieved
     * since we are exposing a rest api end points to get data we can use front end rendering with ajax requests to access data
     * use /api/samples
     */
    @GetMapping("view-all-samples")
    public String viewAllSamples(Model model){
        /*
         * retrieved data is added to Model and then we can access it inside template
         * we can access stored data using key value names
         */
        Iterable<SampleEntity> sampleEntities = sampleService.getAllSamples();
        model.addAttribute("samples", sampleEntities);
        return "view-all-samples";
    }

    /*
     * get data and render view using data retrieved
     * since we are exposing a rest api end points to get data we can use front end rendering with ajax requests to access data
     * use /api/samples/{id}
     */
    @GetMapping("view-single-sample")
    public String viewSample(Model model, @RequestParam("id") Integer id){
        /*
         * retrieved data is added to Model and then we can access it inside template
         * we can access stored data using key value names
         */
        Optional<SampleEntity> sampleEntity = sampleService.getSampleById(id);
        /*
         * check whether the samplesEntity contains data
         */
        if (sampleEntity.isPresent()) {
            model.addAttribute("sample", sampleEntity.get());
        }

        model.addAttribute("id", id);
        return "view-sample";
    }

    /*
     * render add new sample view
     * since we are exposing a rest api end points to update data we can use front end rendering with ajax requests to update data
     */
    @GetMapping("add-new-sample")
    public String addSamplePage() {
        return "add-sample";
    }

    /*
     * handle posted form using add-sample and return notification page
     * since we are exposing a rest api end points to update data we can use front end rendering with ajax requests to update data
     */
    @PostMapping("add-new-sample")
    public String addSample(Model model, @ModelAttribute SampleEntity sampleEntity) {
        sampleService.addSample(sampleEntity);
        model.addAttribute("message", "success");
        return "notification";
    }

    /*
     * load data using api calls
     * use jquery ajax calls to get sample data
     */
    @GetMapping("view-samples-with-jquery")
    public String viewSamplesWithJquery() {
        return "view-samples-with-jquery";
    }

}
