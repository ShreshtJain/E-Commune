package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {

    private Integer id;
    private String code;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
