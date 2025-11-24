package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrefectureDTO {

    private Integer id;
    private String code;
    private String administrativeCode;
    private String name;
    
    private Integer regionId;
    private String regionName;
    
    private Integer countryId;
    private String countryName;
    
    private Integer createdBy;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
