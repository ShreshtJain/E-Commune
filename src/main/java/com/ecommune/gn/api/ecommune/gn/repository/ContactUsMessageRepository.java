package com.ecommune.gn.api.ecommune.gn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommune.gn.api.ecommune.gn.model.ContactUsMessage;

@Repository
public interface ContactUsMessageRepository extends JpaRepository<ContactUsMessage, Long> {
	
}
