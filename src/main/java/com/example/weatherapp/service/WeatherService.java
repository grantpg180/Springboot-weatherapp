package com.example.weatherapp.service;

import java.util.List;

import com.example.weatherapp.model.Response;
import com.example.weatherapp.model.Zipcode;
import com.example.weatherapp.repository.ZipcodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {
    
    @Value("${api_key}")
    private String apiKey;

    @Autowired
    private ZipcodeRepository zipcodeRepository;

    public List<Zipcode> getRecent(){
        return zipcodeRepository.findAll();
    }

    public Response getForcast(String zipcode){
        String url = "http://api.openweathermap.org/data/2.5/weather?zip="+zipcode+"&units=imperial&appid="+apiKey;

        RestTemplate restTemplate = new RestTemplate();
        
        Zipcode zips = new Zipcode();
        zipcodeRepository.save(zips);
        
        try{
            
            return restTemplate.getForObject(url, Response.class);
        } catch (HttpClientErrorException ex){
            Response response = new Response();
            response.setName("error");
            return response;
        }
    }

	
}