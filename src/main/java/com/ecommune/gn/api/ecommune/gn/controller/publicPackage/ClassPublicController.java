package com.ecommune.gn.api.ecommune.gn.controller.publicPackage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommune.gn.api.ecommune.gn.dto.ApiResult;
import com.ecommune.gn.api.ecommune.gn.dto.ClassDTO;
import com.ecommune.gn.api.ecommune.gn.services.ClassService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/public/district")
@RequiredArgsConstructor
@Tag(name = "District", description = "Endpoints for districts")
public class ClassPublicController {
	
    private final ClassService classService;
    
    @Operation(
            summary = "Search districts",
            description = "Search districts of a city by name",
            security = @SecurityRequirement(name = "ApiKeyAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Districts successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping("/{city}")
    public ResponseEntity<ApiResult> searchDistricts(
    		@PathVariable Integer city,
            @RequestParam(required = false) String name
    ) {
        ApiResult apiResult = new ApiResult();
        try {
            List<ClassDTO> districts = classService.getClassesByName(city, name);
            apiResult.setCode(HttpStatus.OK.value());
            apiResult.setMessage("Districts retrieved successfully");
            apiResult.setData(districts);
        } catch (Exception e) {
            apiResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResult.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.status(apiResult.getCode()).body(apiResult);
    }
}
