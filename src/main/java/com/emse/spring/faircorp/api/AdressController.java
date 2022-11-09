package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.service.AdressSearchService;
import com.emse.spring.faircorp.service.dto.ApiGouvAdressDto;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081" )
@RestController // (1)
@RequestMapping("/api/address") // (2)
@Transactional // (3)

public class AdressController {
    private final AdressSearchService address;

    public AdressController(AdressSearchService address){
        this.address = address;
    }
    @GetMapping
    public List<ApiGouvAdressDto> findAll(@RequestParam List<String> keys){
        return address.findAddress(keys);
    }
}
