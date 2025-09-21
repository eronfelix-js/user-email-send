package dev.java10x.email.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTo {

    @JsonProperty("userId")
    private UUID userId;

    private String emailFrom;

    @JsonProperty("emailTo")
    private String emailTo;

    @JsonProperty("subject")
    private String emailSubject;

    @JsonProperty("body")
    private String emailBody;

}
