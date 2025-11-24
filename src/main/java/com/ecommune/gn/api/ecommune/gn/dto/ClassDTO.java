package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {

    private Integer id;
    private String name;
    private String nameNumeric;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    private Integer branchId;
    private String branchName;
    
    private String code;
    private String administrativeCode;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer createdBy;
}
