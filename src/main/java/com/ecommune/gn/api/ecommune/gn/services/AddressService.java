package com.ecommune.gn.api.ecommune.gn.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommune.gn.api.ecommune.gn.dto.AddressDTO;
import com.ecommune.gn.api.ecommune.gn.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
	
	private final AddressRepository addressRepository;

    public List<AddressDTO> searchAddresses(
            String query
    ){
    	return addressRepository.searchAddresses(query);
    }
    

    public AddressDTO lookupAddress(BigDecimal lat, BigDecimal lng)
    {
    	return addressRepository.lookupAddress(lat, lng);
    }
    
}
