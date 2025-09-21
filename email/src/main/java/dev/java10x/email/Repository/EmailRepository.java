package dev.java10x.email.Repository;

import dev.java10x.email.Entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailEntity,UUID>{
}
