package com.ecommune.gn.api.ecommune.gn.controller.publicPackage;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommune.gn.api.ecommune.gn.dto.AddressDTO;
import com.ecommune.gn.api.ecommune.gn.dto.ApiResult;
import com.ecommune.gn.api.ecommune.gn.services.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/public/address")
@RequiredArgsConstructor
@Tag(name = "Address", description = "Endpoints for addresses")
public class AddressPublicController {
	
    private final AddressService addresservice;
    
    @Operation(
            summary = "Search addresses",
            description = "Search addresses by filter (country name, region name, prefecture name, city name, district name, sector name, "
            		+ "complete_text, postal code, Latitude, Longitude)",
            security = @SecurityRequirement(name = "ApiKeyAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping("/search")
    public ResponseEntity<ApiResult> searchAddressByFilter(
    		@RequestParam(required = false) String query
    ) {
        ApiResult apiResult = new ApiResult();
        try {
            List<AddressDTO> addresses = addresservice.searchAddresses(query);
            apiResult.setCode(HttpStatus.OK.value());
            apiResult.setMessage("Addresses retrieved successfully");
            apiResult.setData(addresses);
        } catch (Exception e) {
            apiResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResult.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.status(apiResult.getCode()).body(apiResult);
    }
    
    @Operation(
            summary = "Lookup address",
            description = "Lookup closest address by latitude and longitude",
            security = @SecurityRequirement(name = "ApiKeyAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping("/lookup")
    public ResponseEntity<ApiResult> lookupAddress(
    	       @RequestParam("lat") BigDecimal lat,
    	       @RequestParam("lng") BigDecimal lng
    ) {
        ApiResult apiResult = new ApiResult();
        try {
            AddressDTO address = addresservice.lookupAddress(lat,lng);
            apiResult.setCode(HttpStatus.OK.value());
            apiResult.setMessage("Address retrieved successfully");
            apiResult.setData(address);
        } catch (Exception e) {
            apiResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResult.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.status(apiResult.getCode()).body(apiResult);
    }
}

