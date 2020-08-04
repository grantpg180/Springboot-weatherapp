package com.example.weatherapp.repository;

import com.example.weatherapp.model.Zipcode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {
    
}