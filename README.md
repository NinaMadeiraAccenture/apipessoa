# Api Pessoa
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/devsuperior/sds1-wmazoni/blob/master/LICENSE) 

# Sobre o projeto


Api Pessoa é um microserviço de aplicação backend. O microserviço fornece operações crud sobre a pessoa. Disponibilizado autentificação JWT, produção de mensagem no Kafka ao incluir pessoa e Redis Cache para listagem de pessoas. 

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- JWT
- Maven
- Docker: Docker Compose
- Banco de dados: MySQl e Redis
- Kafka e Zookeeper

# Como executar o projeto
 
## Back end
Pré-requisitos: 
- Java 11
- MySQL
- Docker: Docker Compose para O Redis
- Kafka e Zookeeper instalados ou subir os serviços através do docker compose

```bash
# clonar repositório
git clone https://github.com/NinaMadeiraAccenture/apipessoa

# entrar na pasta do projeto back end
cd apipessoa

# executar o projeto
./mvnw spring-boot:run
```

# Autor

Nina Rosa Madeira


