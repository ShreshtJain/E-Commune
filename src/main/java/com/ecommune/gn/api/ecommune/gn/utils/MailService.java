package com.ecommune.gn.api.ecommune.gn.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${spring.mail.email_from}")
    private String emailFrom;
    
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;


    public void sendConfirmationNotification(String to, String text, String url, String subject, String name) {
        Context context = new Context();
        context.setVariable("subject", subject);
        context.setVariable("url", url);
        context.setVariable("text", text);
        context.setVariable("name", name);
        String html = templateEngine.process("email/confirmation-notification", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setFrom(emailFrom);
            helper.setSubject(subject);
            helper.setText(html, true);
            // Ajouter l'image inline (logo)
            // FileSystemResource logo = new FileSystemResource(new File("src/main/resources/static/images/logo_smac.jpg"));
            // helper.addInline("logo_smac", logo); // "logo_smac" doit correspondre à cid:logo_smac dans le HTML
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erreur d'envoi de l'email de confirmation");
        }

    }

}
