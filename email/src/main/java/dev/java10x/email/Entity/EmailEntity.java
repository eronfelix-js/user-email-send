package dev.java10x.email.Entity;

import dev.java10x.email.Enum.EmailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_email")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "email_id", nullable = false)
    private UUID emailId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "email_from", nullable = false)
    private String emailFrom;

    @Column(name = "email_to", nullable = false)
    private String emailTo;

    @Column(name = "email_subject")
    private String emailSubject;

    @Column(name = "email_body", columnDefinition = "TEXT")
    private String emailBody;

    @Column(name = "send_date")
    private LocalDateTime sandDate;

    @Column(name = "email_status")
    @Enumerated(EnumType.STRING)
    private EmailStatus emailStatus;
}
