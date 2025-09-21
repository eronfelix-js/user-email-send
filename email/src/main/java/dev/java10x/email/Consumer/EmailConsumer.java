package dev.java10x.email.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.java10x.email.Dto.EmailDTo;
import dev.java10x.email.Entity.EmailEntity;
import dev.java10x.email.Service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void listerEmailQueue(@Payload EmailDTo emailDTo){
        log.info("Dados recebidos do DTO: {}", emailDTo);

        var email = new EmailEntity();
        BeanUtils.copyProperties(emailDTo, email,
                "emailTo", "subject", "body", "userId");

        // Mapeamento explícito
        email.setEmailTo(emailDTo.getEmailTo());
        email.setEmailSubject(emailDTo.getEmailSubject());
        email.setEmailBody(emailDTo.getEmailBody());
        email.setUserId(emailDTo.getUserId());

        log.info("Email entity após conversão: {}", email);
        emailService.sendEmail(email);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
