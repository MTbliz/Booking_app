package com.example.booking_app.contoller;

import com.example.booking_app.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigurationController {

    @Autowired
    ConfigurationRepository configurationRepository;

    @PostMapping("/configuration")

    public @ResponseBody String addRecordsToDatabase(){
      return configurationRepository.insertInitialData();
    }
}
