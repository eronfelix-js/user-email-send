package dev.java10x.user.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Tb_User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private static final Long serialVersion = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID UserId;
    private String name;
    private String email;
}
