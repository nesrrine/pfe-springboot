package pfe.jwt_spring.email;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Adresse du serveur SMTP réel
        mailSender.setPort(587); // Port SMTP habituel pour les connexions sécurisées TLS/SSL
        mailSender.setUsername("nesrineeromdhami158@gmail.com"); // Votre nom d'utilisateur SMTP
        mailSender.setPassword("tjbhpssdosewsmrp"); // Votre mot de passe SMTP

        // Configuration des propriétés supplémentaires, si nécessaire
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // Active les logs de débogage pour les e-mails

        return mailSender;
    }    }
