User Email Send - Microservice
📌 Descrição

user-email-send é um microserviço desenvolvido em Java/Spring Boot para estudos e prática de arquitetura de microsserviços.

O serviço tem como objetivo principal:

Receber informações de usuários de outro microserviço via RabbitMQ.

Armazenar informações de e-mails no PostgreSQL.

Enviar e-mails usando SMTP (Gmail).

Este projeto é ideal para aprendizado de integração entre filas de mensageria, persistência de dados e envio de e-mails.

🧩 Tecnologias Utilizadas

Java 17+ / Spring Boot 3.x

RabbitMQ (Mensageria assíncrona)

PostgreSQL (Persistência de dados)

Spring Data JPA / Hibernate (Mapeamento objeto-relacional)

JavaMailSender (Envio de e-mails via SMTP)

Flyway (Migrações de banco de dados)

Lombok (Redução de boilerplate)

⚙️ Funcionalidades

Receber mensagens de outro microserviço

O microserviço de cadastro de usuários envia mensagens contendo informações de e-mail via RabbitMQ.

O user-email-send consome essas mensagens e cria registros no banco de dados.

Persistir e-mails no banco PostgreSQL

Cada e-mail enviado é registrado na tabela tb_email.

A entidade contém informações como emailFrom, emailTo, emailSubject, emailBody, sendDate e emailStatus.

Enviar e-mails

O serviço envia e-mails usando SMTP e atualiza o status (SENT ou FAILED) no banco de dados.

Suporte a e-mails simples (texto).

🗄️ Estrutura do Banco de Dados

Tabela principal: tb_email

Coluna	Tipo	Descrição
email_id	UUID	Identificador único do e-mail (gerado automaticamente)
user_id	UUID	Identificador do usuário destinatário
email_from	VARCHAR	E-mail remetente
email_to	VARCHAR	E-mail destinatário
email_subject	VARCHAR	Assunto do e-mail
email_body	VARCHAR	Conteúdo do e-mail
send_date	TIMESTAMP	Data/hora do envio
email_status	VARCHAR	Status do e-mail (SENT, FAILED)

Dica: Para PostgreSQL, habilitar extensão pgcrypto se usar UUID gerado pelo banco:

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

🔄 Arquitetura
[User Service] ---> RabbitMQ ---> [User Email Send Service] ---> PostgreSQL
                                           |
                                           v
                                        SMTP Server


O User Service envia a mensagem via RabbitMQ.

O Email Service consome a mensagem, persiste e envia o e-mail.

Status de envio é atualizado no banco.

⚡ Configurações importantes
application.yml

Banco de dados:

spring.datasource.url=jdbc:postgresql://localhost:5432/ms_email_ms
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


RabbitMQ:

spring.rabbitmq.addresses=amqps://LEOPARD_CLOUDAMQP_URL
spring.rabbitmq.username=USERNAME
spring.rabbitmq.password=PASSWORD
spring.rabbitmq.virtual-host=VHOST


SMTP:

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${EMAIL_USERNAME}
spring.mail.password=${EMAIL_APP_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true

📦 Rodando a Aplicação

Banco de dados: certifique-se que PostgreSQL está rodando e o banco ms_email_ms foi criado.

RabbitMQ: configure as credenciais no application.yml.

E-mail: configure variáveis de ambiente:

export EMAIL_USERNAME=seuemail@gmail.com
export EMAIL_APP_PASSWORD=senhaApp


Executar aplicação:

mvn clean spring-boot:run


Enviar mensagem de teste pelo microserviço de cadastro ou diretamente no RabbitMQ para verificar envio de e-mail.

📌 Boas práticas aplicadas

Microserviço assíncrono usando RabbitMQ.

Persistência robusta com JPA + PostgreSQL.

Tratamento de status de envio (SENT/FAILED).

Uso de UUID como chave primária.

Configuração de envio seguro de e-mails via SMTP TLS.

🔧 Próximos Passos para Estudos

Suporte a HTML e anexos no e-mail usando MimeMessageHelper.

Implementar fila de retry para e-mails que falharam.

Criar endpoint REST para consultar status de envio.

Adicionar monitoramento/logging centralizado (ex: ELK ou Prometheus).

Integrar com Docker Compose para RabbitMQ + PostgreSQL + Email Service.
