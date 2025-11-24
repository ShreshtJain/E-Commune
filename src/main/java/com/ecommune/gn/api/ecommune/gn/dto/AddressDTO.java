package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Integer id;
    private String code;
    private String number;
    private String completeText;
    
    private Integer countryId;
    private String countryName;
    
    private Integer regionId;
    private String regionName;
    
    private Integer prefectureId;
    private String prefectureName;
    
    private Integer branchId;
    private String branchName;
    private String postalCode; // branch code
    
    private Integer classId;
    private String className;
    
    private Integer sectionId;
    private String sectionName;
    
    private Integer roadId;
    private String roadName;
    
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String owner;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
