package com.ecommune.gn.api.ecommune.gn.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecommune.gn.api.ecommune.gn.dto.RegionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RegionService {

    private final JdbcTemplate jdbcTemplate;
    
    public RegionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<RegionDTO> getRegionsByName(String name) {
        String baseSql = """
                SELECT 
                    r.id,
                    r.country_id,
                    c.name AS country_name,
                    r.code,
                    r.name,
                    r.created_by,
                    r.latitude,
                    r.longitude,
                    r.is_capital,
                    r.created_at,
                    r.updated_at
                FROM regions r
                LEFT JOIN countries c ON r.country_id = c.id
                """;

        if (name == null || name.trim().isEmpty()) {
            // No filter
            return jdbcTemplate.query(baseSql, RegionService::mapRowToRegionDTO);
        } else {
            // Filter by name
            String filteredSql = baseSql + " WHERE r.name LIKE ?";
            return jdbcTemplate.query(filteredSql, RegionService::mapRowToRegionDTO, "%" + name + "%");
        }
    }
    
    private static RegionDTO mapRowToRegionDTO(ResultSet rs, int rowNum) throws SQLException {
        return new RegionDTO(
                rs.getInt("id"),
                rs.getInt("country_id"),
                rs.getString("country_name"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getInt("created_by"),
                rs.getBigDecimal("latitude"),
                rs.getBigDecimal("longitude"),
                rs.getBoolean("is_capital"),
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
        );
    }
}
