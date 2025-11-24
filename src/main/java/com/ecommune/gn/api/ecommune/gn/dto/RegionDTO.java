package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {

    private Integer id;
    
    private Integer countryId;
    private String countryName;
    
    private String code;
    private String name;
    private Integer createdBy;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean isCapital;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
