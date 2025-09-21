package dev.java10x.user.Producer;

import dev.java10x.user.dto.EmailDto;
import dev.java10x.user.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProduce {
    private final RabbitTemplate rabbitTemplate;
    private final String routingKey = "email-queue";


    public void PublishEvent(User user){
        var emailDt0 = new EmailDto();
        emailDt0.setUserId(user.getUserId());
        emailDt0.setEmailTo(user.getEmail());
        emailDt0.setSubject("Seja bem vindo a plataforma");
        emailDt0.setBody("Olá,"+ user.getName()+",\n" +
                "\n" +
                "É um prazer ter você conosco! \uD83D\uDC99\n" +
                "Estamos muito felizes em recebê-lo(a) e esperamos que sua experiência seja incrível ao nosso lado.\n" +
                "\n" +
                "A partir de agora, você poderá aproveitar [benefícios/recursos principais]. Nossa equipe está à disposição para ajudar no que for necessário.\n" +
                "\n" +
                "Se tiver qualquer dúvida, é só responder este e-mail — teremos prazer em ajudar.\n" +
                "\n" +
                "Seja bem-vindo(a)! \uD83D\uDE80\n" +
                "\n" +
                "Atenciosamente,");
        rabbitTemplate.convertAndSend("",routingKey,emailDt0 );
    }
}
