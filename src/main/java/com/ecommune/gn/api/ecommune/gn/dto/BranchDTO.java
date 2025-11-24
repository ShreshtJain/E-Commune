package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    private Integer id;
    private String name;
    private String code;
    private String administrativeCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
   
    private Integer countryId;
    private String countryName;
    private Integer regionId;
    private String regionName;
    private Integer prefectureId;
    private String prefectureName;
    
    private String schoolName;
    private String email;
    private String mobileno;
    private String currency;
    private String symbol;
    private Integer currencyFormats;
    private Integer symbolPosition;
    private String city;
    private String state;
    private String address;
    private Integer stuGenerate;
    private String stuUsernamePrefix;
    private String stuDefaultPassword;
    private Integer grdGenerate;
    private String grdUsernamePrefix;
    private String grdDefaultPassword;
    private Boolean teacherRestricted;
    private Float dueDays;
    private Integer dueWithFine;
    private String translation;
    private String timezone;
    private String weekends;
    private Boolean regPrefixEnable;
    private Integer studentLogin;
    private Integer parentLogin;
    private Integer teacherMobileVisible;
    private Integer teacherEmailVisible;
    private Integer regStartFrom;
    private String institutionCode;
    private Integer regPrefixDigit;
    private Boolean offlinePayments;
    private Integer attendanceType;
    private Integer showOwnQuestion;
    private Integer status;
    private Integer uniqueRoll;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
