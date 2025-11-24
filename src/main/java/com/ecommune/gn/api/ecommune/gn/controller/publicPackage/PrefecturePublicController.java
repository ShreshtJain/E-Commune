package com.ecommune.gn.api.ecommune.gn.controller.publicPackage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommune.gn.api.ecommune.gn.dto.ApiResult;
import com.ecommune.gn.api.ecommune.gn.dto.PrefectureDTO;
import com.ecommune.gn.api.ecommune.gn.services.PrefectureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/public/prefecture")
@RequiredArgsConstructor
@Tag(name = "Prefecture", description = "Endpoints for prefectures")
public class PrefecturePublicController {
	
    private final PrefectureService prefectureService;
    
    @Operation(
            summary = "Search prefectures",
            description = "Search prefectures by name",
            security = @SecurityRequirement(name = "ApiKeyAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prefectures successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
    public ResponseEntity<ApiResult> searchPrefectures(
            @RequestParam(required = false) String name
    ) {
        ApiResult apiResult = new ApiResult();
        try {
            List<PrefectureDTO> prefectures = prefectureService.getPrefecturesByName(name);
            apiResult.setCode(HttpStatus.OK.value());
            apiResult.setMessage("Prefectures retrieved successfully");
            apiResult.setData(prefectures);
        } catch (Exception e) {
            apiResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResult.setMessage("Error: " + e.getMessage());
        }
        return ResponseEntity.status(apiResult.getCode()).body(apiResult);
    }

}
