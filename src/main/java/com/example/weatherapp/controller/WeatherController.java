package com.example.weatherapp.controller;

import java.util.List;

import com.example.weatherapp.model.Request;
import com.example.weatherapp.model.Response;
import com.example.weatherapp.model.Zipcode;
import com.example.weatherapp.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public String getIndex(Model model){

        List<Zipcode> zipCodeList = weatherService.getRecent();
        model.addAttribute("request", new Request());
        model.addAttribute("zip_codes", zipCodeList);
        return "index";
    }

    @PostMapping
    public String postIndex(Request request, Model model){
        List<Zipcode> zipCodeList = weatherService.getRecent();

        Response data = weatherService.getForcast(request.getZipcode());
        model.addAttribute("data", data);
        model.addAttribute("zip_codes", zipCodeList);
        return "index";
    }
    
}