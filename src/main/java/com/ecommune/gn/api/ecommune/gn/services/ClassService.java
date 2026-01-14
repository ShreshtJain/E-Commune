package com.ecommune.gn.api.ecommune.gn.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecommune.gn.api.ecommune.gn.dto.ClassDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassService {

private final JdbcTemplate jdbcTemplate;
    
	public List<ClassDTO> getClassesByName(Integer city, String name) {
		String baseSql = """
				SELECT
						c.id,
				    	c.name,
				    	c.name_numeric,
				        c.created_at,
				        c.updated_at,
				        c.branch_id,
				        b.name AS branch_name,
				        c.code,
				        c.administrative_code,
				        c.longitude,
				        c.latitude,
				        c.created_by
				    FROM class c
				    LEFT JOIN branch b ON c.branch_id = b.id
				    WHERE c.branch_id = ?
				    """;

	    if (name != null && !name.trim().isEmpty()) {
	        baseSql += " AND LOWER(c.name) LIKE LOWER(?) ORDER BY c.name ASC";
	        return jdbcTemplate.query(baseSql, ClassService::mapRowToClassDTO, city, "%" + name + "%");
	    } else {
	    	baseSql+= " ORDER BY c.name ASC";
	        return jdbcTemplate.query(baseSql, ClassService::mapRowToClassDTO, city);
	    }
	}
    
    private static ClassDTO mapRowToClassDTO(ResultSet rs, int rowNum) throws SQLException {
        ClassDTO dto = new ClassDTO();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setNameNumeric(rs.getString("name_numeric"));
        dto.setCreatedAt(rs.getTimestamp("created_at") != null ? 
            rs.getTimestamp("created_at").toLocalDateTime() : null);
        dto.setUpdatedAt(rs.getTimestamp("updated_at") != null ? 
            rs.getTimestamp("updated_at").toLocalDateTime() : null);
        dto.setBranchId(rs.getInt("branch_id"));
        dto.setBranchName(rs.getString("branch_name"));
        dto.setCode(rs.getString("code"));
        dto.setAdministrativeCode(rs.getString("administrative_code"));
        dto.setLongitude(rs.getBigDecimal("longitude"));
        dto.setLatitude(rs.getBigDecimal("latitude"));
        dto.setCreatedBy(rs.getInt("created_by"));
        return dto;
    }
}
