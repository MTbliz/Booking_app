package com.example.booking_app.contoller;

import com.example.booking_app.model.Reservation;
import com.example.booking_app.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ConfigurationController {

    @Autowired
    ConfigurationRepository configurationRepository;

    @PostMapping("/configuration")

    public @ResponseBody String addRecordsToDatabase(){
      return configurationRepository.someUpdate();
    }
}
