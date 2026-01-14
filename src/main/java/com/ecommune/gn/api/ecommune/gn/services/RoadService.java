package com.ecommune.gn.api.ecommune.gn.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecommune.gn.api.ecommune.gn.dto.RoadDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoadService {

private final JdbcTemplate jdbcTemplate;
    
public List<RoadDTO> getRoadsByName(String name) {
    String sql = """
            SELECT
                r.id,
                r.code,
                r.name,
                r.type_id,
                rt.name AS type_name,
                r.country_id,
                c.name AS country_name,
                r.region_id,
                rg.name AS region_name,
                r.prefecture_id,
                p.name AS prefecture_name,
                r.branch_id,
                b.name AS branch_name,
                r.class_id,
                cl.name AS class_name,
                r.section_id,
                s.name AS section_name,
                ST_AsText(r.geom) AS geom,  -- Convert LINESTRING to readable text
                r.created_by,
                r.created_at,
                r.updated_at
            FROM roads r
            LEFT JOIN road_types rt ON r.type_id = rt.id
            LEFT JOIN countries c ON r.country_id = c.id
            LEFT JOIN regions rg ON r.region_id = rg.id
            LEFT JOIN prefectures p ON r.prefecture_id = p.id
            LEFT JOIN branch b ON r.branch_id = b.id
            LEFT JOIN class cl ON r.class_id = cl.id
            LEFT JOIN section s ON r.section_id = s.id
            WHERE 1 = 1
            """;

    List<Object> params = new ArrayList<>();

    if (name != null && !name.trim().isEmpty()) {
        sql += " AND LOWER(r.name) LIKE LOWER(?)";
        params.add("%" + name + "%");
    }

    sql+= " ORDER BY r.name ASC";
    
    return jdbcTemplate.query(sql, RoadService::mapRowToRoadDTO, params.toArray());
}
    
    private static RoadDTO mapRowToRoadDTO(ResultSet rs, int rowNum) throws SQLException {
        return new RoadDTO(
            rs.getInt("id"),
            rs.getString("code"),
            rs.getString("name"),

            rs.getInt("type_id"),
            rs.getString("type_name"),

            rs.getInt("country_id"),
            rs.getString("country_name"),

            rs.getInt("region_id"),
            rs.getString("region_name"),

            rs.getInt("prefecture_id"),
            rs.getString("prefecture_name"),

            rs.getInt("branch_id"),
            rs.getString("branch_name"),

            rs.getInt("class_id"),
            rs.getString("class_name"),

            rs.getInt("section_id"),
            rs.getString("section_name"),

            rs.getString("geom"), // MariaDB/MySQL LINESTRING as String (e.g., "LINESTRING(lat lon, lat lon, ...)")
            rs.getInt("created_by"),

            rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
            rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
        );
    }
}
