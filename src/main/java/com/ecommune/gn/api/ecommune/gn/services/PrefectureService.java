package com.ecommune.gn.api.ecommune.gn.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecommune.gn.api.ecommune.gn.dto.PrefectureDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrefectureService {

    private final JdbcTemplate jdbcTemplate;

    public List<PrefectureDTO> getPrefecturesByName(String name) {
        String baseSql = """
                SELECT 
                    p.id,
                    p.code,
                    p.administrative_code,
                    p.name,
                    p.region_id,
                    r.name AS region_name,
                    p.country_id,
                    c.name AS country_name,
                    p.created_by,
                    p.latitude,
                    p.longitude,
                    p.created_at,
                    p.updated_at
                FROM prefectures p
                LEFT JOIN regions r ON p.region_id = r.id
                LEFT JOIN countries c ON p.country_id = c.id
                """;

        if (name == null || name.trim().isEmpty()) {
            // No filter → return all prefectures
            String sql = baseSql + " ORDER BY p.name ASC";
            return jdbcTemplate.query(sql, PrefectureService::mapRowToPrefectureDTO);
        } else {
            // Filter by name
            String filteredSql = baseSql + " WHERE p.name LIKE ? ORDER BY p.name ASC";
            return jdbcTemplate.query(filteredSql, PrefectureService::mapRowToPrefectureDTO, "%" + name + "%");
        }
    }

    private static PrefectureDTO mapRowToPrefectureDTO(ResultSet rs, int rowNum) throws SQLException {
        return new PrefectureDTO(
                rs.getInt("id"),
                rs.getString("code"),
                rs.getString("administrative_code"),
                rs.getString("name"),
                rs.getInt("region_id"),
                rs.getString("region_name"),
                rs.getInt("country_id"),
                rs.getString("country_name"),
                rs.getInt("created_by"),
                rs.getBigDecimal("latitude"),
                rs.getBigDecimal("longitude"),
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
        );
    }
}

