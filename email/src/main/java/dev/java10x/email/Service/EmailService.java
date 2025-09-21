package dev.java10x.email.Service;

import dev.java10x.email.Entity.EmailEntity;
import dev.java10x.email.Enum.EmailStatus;
import dev.java10x.email.Repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    @Transactional
    public EmailEntity sendEmail(EmailEntity emailEntity) {
        try {
            // Log dos dados recebidos
            log.info("Preparando para enviar email. Dados recebidos: To={}, Subject={}, Body={}",
                    emailEntity.getEmailTo(),
                    emailEntity.getEmailSubject(),
                    emailEntity.getEmailBody());

            // Configura os dados do email
            emailEntity.setEmailFrom(emailFrom);
            emailEntity.setSandDate(LocalDateTime.now());
            emailEntity.setEmailStatus(EmailStatus.PENDING);

            // Validações
            if (emailEntity.getEmailTo() == null || emailEntity.getEmailTo().isEmpty()) {
                throw new IllegalArgumentException("Email destinatário não pode ser vazio");
            }
            if (emailEntity.getEmailBody() == null || emailEntity.getEmailBody().isEmpty()) {
                throw new IllegalArgumentException("Corpo do email não pode ser vazio");
            }

            // Cria a mensagem usando MimeMessage para suporte completo
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailFrom);
            helper.setTo(emailEntity.getEmailTo());
            helper.setSubject(emailEntity.getEmailSubject());
            helper.setText(emailEntity.getEmailBody(), true);

            log.info("Email configurado e pronto para envio: From={}, To={}, Subject={}",
                    emailFrom,
                    emailEntity.getEmailTo(),
                    emailEntity.getEmailSubject());

            // Envia o email
            mailSender.send(message);

            // Atualiza o status para enviado
            emailEntity.setEmailStatus(EmailStatus.SENT);
            log.info("Email enviado com sucesso para: {}", emailEntity.getEmailTo());

        } catch (MessagingException e) {
            emailEntity.setEmailStatus(EmailStatus.FAILED);
            log.error("Erro ao configurar email para {}: {}", emailEntity.getEmailTo(), e.getMessage());
            throw new RuntimeException("Erro ao configurar email", e);
        } catch (Exception e) {
            emailEntity.setEmailStatus(EmailStatus.FAILED);
            log.error("Erro ao enviar email para {}: {}", emailEntity.getEmailTo(), e.getMessage());
        }

        // Salva o resultado no banco
        return emailRepository.save(emailEntity);
    }
}