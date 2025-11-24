package com.ecommune.gn.api.ecommune.gn.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommune.gn.api.ecommune.gn.dto.ContactUsRequestDTO;
import com.ecommune.gn.api.ecommune.gn.model.ContactUsMessage;
import com.ecommune.gn.api.ecommune.gn.model.NewsletterSubscriber;
import com.ecommune.gn.api.ecommune.gn.repository.ContactUsMessageRepository;
import com.ecommune.gn.api.ecommune.gn.repository.NewsletterSubscriberRepository;
import com.ecommune.gn.api.ecommune.gn.utils.EncryptionService;
import com.ecommune.gn.api.ecommune.gn.utils.MailService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsletterService {

	@Autowired
    EncryptionService encryptionService;
	
    @Value("${samory.urls.site}")
    private String url;
    
    @Value("${spring.mail.email_to}")
    private String emailTo;
    
	private final NewsletterSubscriberRepository subscriberRepo;
    private final ContactUsMessageRepository contactRepo;
    private final MailService mailService;
    
    @Transactional
    public NewsletterSubscriber subscribe(String email) {
        Optional<NewsletterSubscriber> existingSubscriber = subscriberRepo.findByEmail(encryptionService.encrypt(email));
        
        if (existingSubscriber.isPresent()) {
            throw new IllegalArgumentException("Email is already subscribed");
        }
        
        NewsletterSubscriber subscriber = new NewsletterSubscriber();
        subscriber.setEmail(encryptionService.encrypt(email));
        subscriber.setSubscribedAt(LocalDateTime.now());
        subscriber.setActive(true);
        NewsletterSubscriber returnSubscriber = subscriberRepo.save(subscriber);
        
        mailService.sendConfirmationNotification(
        	    email,
        	    "Thank you for subscribing to our newsletter!",
        	    url,
        	    "Confirm your newsletter subscription",
        	    null
        	);
        
        return returnSubscriber;
    }

    public void unsubscribe(String email) {
        NewsletterSubscriber subscriber = subscriberRepo.findByEmail(encryptionService.encrypt(email))
                .orElseThrow(() -> new IllegalArgumentException("Email not found in subscribers"));
        
        subscriberRepo.delete(subscriber);
    }

    @Transactional
    public ContactUsMessage sendContactMessage(ContactUsRequestDTO request) {
        ContactUsMessage message = new ContactUsMessage();
        
        message.setName(request.getName());
        message.setEmail(encryptionService.encrypt(request.getEmail()));
        message.setPhone(encryptionService.encrypt(request.getPhone()));
        message.setSubject(request.getSubject());
        message.setMessage(encryptionService.encrypt(request.getMessage()));
        message.setCreatedAt(LocalDateTime.now());
        
        ContactUsMessage savedMessage = contactRepo.save(message);

        mailService.sendConfirmationNotification(
                emailTo,
                "You have received a new contact message",
                null,                    // No confirmation URL needed
                "New Contact Us Message: " + request.getSubject(),
                request.getName()
            );
        
        return savedMessage;
    }
}
