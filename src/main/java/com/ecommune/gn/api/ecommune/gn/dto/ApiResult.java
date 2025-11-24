package com.ecommune.gn.api.ecommune.gn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {
    private int code;
    private String message;
    private Object data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int pageSize;
}
