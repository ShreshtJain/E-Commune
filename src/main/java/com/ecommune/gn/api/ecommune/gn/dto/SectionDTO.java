package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {

    private Integer id;
    private String name;
    private String capacity;
  
    private Integer branchId;
    private String branchName;
    
    private String code;
    private String administrativeCode;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer createdBy;
}
