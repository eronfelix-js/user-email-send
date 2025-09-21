package dev.java10x.user.dto;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class EmailDto {

    private UUID UserId;
    private String emailTo;
    private String subject;
    private String body;

    public EmailDto(UUID id, UUID userId, String emailTo, String subject, String body) {
        UserId = userId;
        this.emailTo = emailTo;
        this.subject = subject;
        this.body = body;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
