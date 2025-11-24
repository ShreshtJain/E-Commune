package com.ecommune.gn.api.ecommune.gn.controller.publicPackage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommune.gn.api.ecommune.gn.dto.ApiResult;
import com.ecommune.gn.api.ecommune.gn.dto.RegionDTO;
import com.ecommune.gn.api.ecommune.gn.services.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/public/region")
@RequiredArgsConstructor
@Tag(name = "Region", description = "Endpoints for regions")
public class RegionPublicController {
	
    private final RegionService regionService;
    
    @Operation(
            summary = "Search regions",
            description = "Search regions by name",
            security = @SecurityRequirement(name = "ApiKeyAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Regions successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
    public ResponseEntity<ApiResult> searchRegions(
            @RequestParam(required = false) String name
    ) {
        ApiResult apiResult = new ApiResult();
        try {
            List<RegionDTO> regions = regionService.getRegionsByName(name);
            apiResult.setCode(HttpStatus.OK.value());
            apiResult.setMessage("Regions retrieved successfully");
            apiResult.setData(regions);
        } catch (Exception e) {
            apiResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResult.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.status(apiResult.getCode()).body(apiResult);
    }

}
