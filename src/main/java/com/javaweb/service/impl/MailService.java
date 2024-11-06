package com.javaweb.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.from}")
    private String emailFrom;

    public void sendConfirmLink(@Email String email, Long userId, String secretCode) throws MessagingException, UnsupportedEncodingException {
        log.info("Sending email confirm account...");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        String linkConfirm = String.format("http://localhost:8088/auth/reset-password?secretKey=%s", secretCode);

        Map<String, Object> properties = new HashMap<>();
        properties.put("confirmLink", linkConfirm);

        log.info("Reset password code confirm: {}", linkConfirm);

        context.setVariables(properties);
        helper.setFrom(emailFrom, "Chu Thắng");
        helper.setTo(email);
        helper.setSubject("Please confirm your account");

        String html = templateEngine.process("users/confirm-reset-password.html", context);
        helper.setText(html, true);
        mailSender.send(mimeMessage);
        log.info("Email has been send successfully, recipients ={}", email);
    }
}
