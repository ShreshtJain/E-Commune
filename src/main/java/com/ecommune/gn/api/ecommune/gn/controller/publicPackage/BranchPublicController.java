package com.ecommune.gn.api.ecommune.gn.controller.publicPackage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommune.gn.api.ecommune.gn.dto.ApiResult;
import com.ecommune.gn.api.ecommune.gn.dto.BranchDTO;
import com.ecommune.gn.api.ecommune.gn.services.BranchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/public/city")
@RequiredArgsConstructor
@Tag(name = "City", description = "Endpoints for cities")
public class BranchPublicController {
	
    private final BranchService branchService;
    
    @Operation(
            summary = "Search cities",
            description = "Search cities by name",
            security = @SecurityRequirement(name = "ApiKeyAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cities successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
    public ResponseEntity<ApiResult> searchCities(
            @RequestParam(required = false) String name
    ) {
        ApiResult apiResult = new ApiResult();
        try {
            List<BranchDTO> cities = branchService.getBranchesByName(name);
            apiResult.setCode(HttpStatus.OK.value());
            apiResult.setMessage("Cities retrieved successfully");
            apiResult.setData(cities);
        } catch (Exception e) {
            apiResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResult.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.status(apiResult.getCode()).body(apiResult);
    }
	
}
