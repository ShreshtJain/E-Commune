package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoadDTO {

    private Integer id;
    private String code;
    private String name;

    private Integer typeId;
    private String typeName;

    private Integer countryId;
    private String countryName;

    private Integer regionId;
    private String regionName;


    private Integer prefectureId;
    private String prefectureName;

    private Integer branchId;
    private String branchName;
    
    private Integer classId;
    private String className;

    private Integer sectionId;
    private String sectionName;

    // Since MariaDB/MySQL LINESTRING can't be directly mapped, use String or custom geometry type
    private String geom; 

    private Integer createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
