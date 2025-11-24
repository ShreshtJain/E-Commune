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
            String countryName,
            String regionName,
            String prefectureName,
            String cityName,     // branch
            String districtName, // class
            String sectorName,   // section
            String completeText,  // complete_text
            String postalCode,   // branch code
            BigDecimal lat, 
            BigDecimal lng
    ){
    	return addressRepository.searchAddresses(countryName, regionName, prefectureName, cityName, districtName, sectorName, completeText, postalCode, lat, lng);
    }
    
}
