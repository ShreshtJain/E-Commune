package com.ecommune.gn.api.ecommune.gn.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecommune.gn.api.ecommune.gn.dto.BranchDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchService {
	
    private final JdbcTemplate jdbcTemplate;
    
    public List<BranchDTO> getBranchesByName(String name) {
        String sql = """
        		SELECT
        			b.id, b.name, b.code, b.administrative_code,
        			b.latitude, b.longitude,
    
        			b.country_id, c.name AS country_name,
        			b.region_id, r.name AS region_name,
        			b.prefecture_id, p.name AS prefecture_name,
    
        			b.school_name, b.email, b.mobileno,
        			b.currency, b.symbol, b.currency_formats, b.symbol_position,
        			b.city, b.state, b.address,
        			b.stu_generate, b.stu_username_prefix, b.stu_default_password,
        			b.grd_generate, b.grd_username_prefix, b.grd_default_password,
        			b.teacher_restricted, b.due_days, b.due_with_fine,
        			b.translation, b.timezone, b.weekends,
        			b.reg_prefix_enable, b.student_login, b.parent_login,
        			b.teacher_mobile_visible, b.teacher_email_visible, b.reg_start_from,
        			b.institution_code, b.reg_prefix_digit, b.offline_payments,
        			b.attendance_type, b.show_own_question, b.status, b.unique_roll,
        			b.created_at, b.updated_at
        			FROM branch b
        			LEFT JOIN countries c ON b.country_id = c.id
        			LEFT JOIN regions r ON b.region_id = r.id
        			LEFT JOIN prefectures p ON b.prefecture_id = p.id
        		""";
        
        if (name == null || name.trim().isEmpty()) {
            return jdbcTemplate.query(sql, BranchService::mapRowToBranchDTO);
        } else {
            // Filter by name
            String filteredSql = sql + " WHERE b.name LIKE ?";
            return jdbcTemplate.query(filteredSql, BranchService::mapRowToBranchDTO, "%" + name + "%");
        }
    }
    
	private static BranchDTO mapRowToBranchDTO(ResultSet rs, int rowNum) throws SQLException {
	    return new BranchDTO(
	        rs.getInt("id"),
	        rs.getString("name"),
	        rs.getString("code"),
	        rs.getString("administrative_code"),
	        rs.getBigDecimal("latitude"),
	        rs.getBigDecimal("longitude"),

	        rs.getInt("country_id"),
	        rs.getString("country_name"),
	        rs.getInt("region_id"),
	        rs.getString("region_name"),
	        rs.getInt("prefecture_id"),
	        rs.getString("prefecture_name"),

	        rs.getString("school_name"),
	        rs.getString("email"),
	        rs.getString("mobileno"),
	        rs.getString("currency"),
	        rs.getString("symbol"),
	        rs.getInt("currency_formats"),
	        rs.getInt("symbol_position"),
	        rs.getString("city"),
	        rs.getString("state"),
	        rs.getString("address"),
	        rs.getInt("stu_generate"),
	        rs.getString("stu_username_prefix"),
	        rs.getString("stu_default_password"),
	        rs.getInt("grd_generate"),
	        rs.getString("grd_username_prefix"),
	        rs.getString("grd_default_password"),
	        rs.getBoolean("teacher_restricted"),
	        rs.getFloat("due_days"),
	        rs.getInt("due_with_fine"),
	        rs.getString("translation"),
	        rs.getString("timezone"),
	        rs.getString("weekends"),
	        rs.getBoolean("reg_prefix_enable"),
	        rs.getInt("student_login"),
	        rs.getInt("parent_login"),
	        rs.getInt("teacher_mobile_visible"),
	        rs.getInt("teacher_email_visible"),
	        rs.getInt("reg_start_from"),
	        rs.getString("institution_code"),
	        rs.getInt("reg_prefix_digit"),
	        rs.getBoolean("offline_payments"),
	        rs.getInt("attendance_type"),
	        rs.getInt("show_own_question"),
	        rs.getInt("status"),
	        rs.getInt("unique_roll"),
	        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
	        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
	    );
	}

}
