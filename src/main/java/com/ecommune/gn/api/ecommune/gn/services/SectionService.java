package com.ecommune.gn.api.ecommune.gn.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecommune.gn.api.ecommune.gn.dto.SectionDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectionService {

	private final JdbcTemplate jdbcTemplate;
	
	public List<SectionDTO> getSectionsByName(Integer district, String name) {
	    String sql = """
	        SELECT
	            s.id,
	            s.name,
	            s.capacity,
	            s.branch_id,
	            b.name AS branch_name,
	            s.code,
	            s.administrative_code,
	            s.longitude,
	            s.latitude,
	            s.created_by
	        FROM section s
	        LEFT JOIN branch b ON s.branch_id = b.id
	        INNER JOIN sections_allocation sa ON sa.section_id = s.id
	        WHERE sa.class_id = ?
	    """;

	    if (name != null && !name.trim().isEmpty()) {
	        sql += " AND LOWER(s.name) LIKE LOWER(?) ORDER BY s.name ASC";
	        return jdbcTemplate.query(
	                sql,
	                SectionService::mapRowToSectionDTO,
	                district,
	                "%" + name + "%"
	        );
	    } else {
	    	sql += "ORDER BY s.name ASC";
	        return jdbcTemplate.query(sql, SectionService::mapRowToSectionDTO, district);
	    }
	}
    
	private static SectionDTO mapRowToSectionDTO(ResultSet rs, int rowNum) throws SQLException {
	    SectionDTO dto = new SectionDTO();
	    dto.setId(rs.getInt("id"));
	    dto.setName(rs.getString("name"));
	    dto.setCapacity(rs.getString("capacity"));
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
