package com.ecommune.gn.api.ecommune.gn.controller.publicPackage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommune.gn.api.ecommune.gn.dto.ApiResult;
import com.ecommune.gn.api.ecommune.gn.dto.ContactUsRequestDTO;
import com.ecommune.gn.api.ecommune.gn.dto.NewsletterRequestDTO;
import com.ecommune.gn.api.ecommune.gn.model.ContactUsMessage;
import com.ecommune.gn.api.ecommune.gn.model.NewsletterSubscriber;
import com.ecommune.gn.api.ecommune.gn.services.NewsletterService;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Hidden
@RestController
@RequestMapping("/api/v1/public/newsletter")
@RequiredArgsConstructor
public class NewsletterPublicController {

    private final NewsletterService newsletterService;

    @PostMapping("/subscribe")
    public ResponseEntity<ApiResult> subscribe(@Valid @RequestBody NewsletterRequestDTO request) {
        ApiResult result = new ApiResult();
        try {
            NewsletterSubscriber subscriber = newsletterService.subscribe(request.getEmail());
            result.setCode(HttpStatus.OK.value());
            result.setMessage("Subscribed successfully");
            result.setData(subscriber);
        } catch (Exception e) {
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Failed to subscribe to the newsletter: "+e.getMessage());
        }
        return ResponseEntity.status(result.getCode()).body(result);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<ApiResult> unsubscribe(@Valid @RequestBody NewsletterRequestDTO request) {
        ApiResult result = new ApiResult();
        try {
            newsletterService.unsubscribe(request.getEmail());
            result.setCode(HttpStatus.OK.value());
            result.setMessage("Unsubscribed successfully");
        } catch (IllegalArgumentException e) {
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Failed to unsubscribe from the newsletter: "+e.getMessage());
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("Unexpected error: "+e.getMessage());
        }
        return ResponseEntity.status(result.getCode()).body(result);
    }
   
    @PostMapping("/contact-us")
    public ResponseEntity<ApiResult> contactUs(@Valid @RequestBody ContactUsRequestDTO request) {
        ApiResult result = new ApiResult();
        try {
            ContactUsMessage message = newsletterService.sendContactMessage(request);
            result.setCode(HttpStatus.OK.value());
            result.setMessage("Contact message sent successfully");
            result.setData(message);
        } catch (IllegalArgumentException e) {
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Failed to send contact us message: "+e.getMessage());
        } catch (Exception e) {
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("Unexpected error: "+e.getMessage());
        }
        return ResponseEntity.status(result.getCode()).body(result);
    }
}
