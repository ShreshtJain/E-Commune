package com.ecommune.gn.api.ecommune.gn.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommune.gn.api.ecommune.gn.dto.AddressDTO;

@Repository
public class AddressRepository {
	
    private final JdbcTemplate jdbcTemplate;

    public AddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AddressDTO> searchAddresses(
            String countryName,
            String regionName,
            String prefectureName,
            String cityName,     // branch
            String districtName, // class
            String sectorName,   // section
            String completeText, // address text
            String postalCode,   // branch code
            BigDecimal lat,
            BigDecimal lng
    ) {
        StringBuilder sql = new StringBuilder("""
            SELECT 
                a.*, 
                c.name AS country_name, 
                r.name AS region_name, 
                p.name AS prefecture_name, 
                b.name AS branch_name, 
                b.code AS postal_code,
                cl.name AS class_name, 
                s.name AS section_name, 
                rd.name AS road_name
            """);

        // Add computed distance only if coordinates are provided
        boolean hasCoordinates = (lat != null && lng != null);
        if (hasCoordinates) {
            sql.append(", SQRT(POW(a.latitude - ?, 2) + POW(a.longitude - ?, 2)) AS distance");
        }

        sql.append("""
            FROM addresses a
            LEFT JOIN countries c ON a.country_id = c.id
            LEFT JOIN regions r ON a.region_id = r.id
            LEFT JOIN prefectures p ON a.prefecture_id = p.id
            LEFT JOIN branch b ON a.branch_id = b.id
            LEFT JOIN class cl ON a.class_id = cl.id
            LEFT JOIN section s ON a.section_id = s.id
            LEFT JOIN roads rd ON a.road_id = rd.id
            WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();

        // If coordinates are used, they must be added first (because of distance expression)
        if (hasCoordinates) {
            params.add(lat);
            params.add(lng);
        }

        // Dynamic filters
        if (countryName != null && !countryName.isBlank()) {
            sql.append(" AND c.name LIKE ?");
            params.add("%" + countryName + "%");
        }
        if (regionName != null && !regionName.isBlank()) {
            sql.append(" AND r.name LIKE ?");
            params.add("%" + regionName + "%");
        }
        if (prefectureName != null && !prefectureName.isBlank()) {
            sql.append(" AND p.name LIKE ?");
            params.add("%" + prefectureName + "%");
        }
        if (cityName != null && !cityName.isBlank()) {
            sql.append(" AND b.name LIKE ?");
            params.add("%" + cityName + "%");
        }
        if (districtName != null && !districtName.isBlank()) {
            sql.append(" AND cl.name LIKE ?");
            params.add("%" + districtName + "%");
        }
        if (sectorName != null && !sectorName.isBlank()) {
            sql.append(" AND s.name LIKE ?");
            params.add("%" + sectorName + "%");
        }
        if (postalCode != null && !postalCode.isBlank()) {
            sql.append(" AND b.code LIKE ?");
            params.add("%" + postalCode + "%");
        }
        if (completeText != null && !completeText.isBlank()) {
            sql.append(" AND a.complete_text LIKE ?");
            params.add("%" + completeText + "%");
        }

        // If lat/lng provided, sort by proximity; else sort by latest update
        if (hasCoordinates) {
            sql.append(" ORDER BY distance ASC LIMIT 1");
            return jdbcTemplate.query(sql.toString(), AddressRepository::mapRowToAddressDTO, params.toArray());
        } 
        
        return jdbcTemplate.query(sql.toString(), AddressRepository::mapRowToAddressDTO, params.toArray());

    }

    private static AddressDTO mapRowToAddressDTO(ResultSet rs, int rowNum) throws SQLException {
        AddressDTO dto = new AddressDTO();
        dto.setId(rs.getInt("id"));
        dto.setCode(rs.getString("code"));
        dto.setNumber(rs.getString("number"));
        dto.setCompleteText(rs.getString("complete_text"));
        
        dto.setCountryId(rs.getInt("country_id"));
        dto.setCountryName(rs.getString("country_name"));
        
        dto.setRegionId(rs.getInt("region_id"));
        dto.setRegionName(rs.getString("region_name"));
        
        dto.setPrefectureId(rs.getInt("prefecture_id"));
        dto.setPrefectureName(rs.getString("prefecture_name"));
        
        dto.setBranchId(rs.getInt("branch_id"));
        dto.setBranchName(rs.getString("branch_name"));
        dto.setPostalCode(rs.getString("postal_code"));
        
        dto.setClassId(rs.getInt("class_id"));
        dto.setClassName(rs.getString("class_name"));
        
        dto.setSectionId(rs.getInt("section_id"));
        dto.setSectionName(rs.getString("section_name"));
        
        dto.setRoadId(rs.getInt("road_id"));
        dto.setRoadName(rs.getString("road_name"));
        
        dto.setLongitude(rs.getBigDecimal("longitude"));
        dto.setLatitude(rs.getBigDecimal("latitude"));
        dto.setOwner(rs.getString("owner"));
        
        dto.setCreatedAt(rs.getTimestamp("created_at") != null 
                ? rs.getTimestamp("created_at").toLocalDateTime() 
                : null);
        dto.setUpdatedAt(rs.getTimestamp("updated_at") != null 
                ? rs.getTimestamp("updated_at").toLocalDateTime() 
                : null);
        
        return dto;
    }

}
