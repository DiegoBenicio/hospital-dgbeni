# 🏥 API de Gestão Hospitalar

API desenvolvida em Java com Spring Boot para cadastro e gerenciamento de médicos e pacientes. O projeto utiliza princípios de Domain-Driven Design (DDD), boas práticas de arquitetura e documentação com Swagger/OpenAPI.

---

## 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot 3.x
* Maven
* JPA/Hibernate
* PostgreSQL
* Swagger/OpenAPI
* Lombok
* Bean Validation
* Springdoc (para documentação)
* JUnit (para testes)
* Git

---

## 🌟 Funcionalidades

* ✅ Cadastro de médicos e pacientes
* 🔍 Consulta de médicos e pacientes
* ✏️ Atualização (total e parcial) dos dados
* ❌ Inativação lógica (soft delete)
* 📄 Documentação automática da API (Swagger UI)

---

## 📁 Estrutura do Projeto

```
com.hospital.dgbeni
├── api                 # Camada de entrada (controllers e DTOs)
├── application         # Casos de uso (services da aplicação)
├── domain
│   ├── medico          # Entidade, repositório e regras de negócio de médicos
│   ├── paciente        # Entidade, repositório e regras de negócio de pacientes
│   ├── service         # Serviços de domínio (validações, regras específicas)
│   ├── exception       # Exceções personalizadas
│   └── shared          # Classes comuns (Endereço, enums, etc.)
└── config              # Configurações do projeto (ex: Swagger)
```

---

## 📦 Instalação e Execução

1. **Clone o projeto**

```bash
git clone https://github.com/seu-usuario/nome-do-repo.git
cd nome-do-repo
```

2. **Configure o banco de dados**

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hospital
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3. **Rode a aplicação**

```bash
./mvnw spring-boot:run
```

---

## 📚 Acessando a Documentação da API

Após subir o projeto, acesse:

```
http://localhost:8080/swagger-ui.html
```

ou

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Validações e Boas Práticas

* Utilização de `@Valid`, `@NotNull`, `@Email`, `@Pattern` etc.
* Respostas padronizadas com códigos HTTP apropriados
* Arquitetura baseada em DDD
* Testes unitários em desenvolvimento

---

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

---

## 👨‍💼 Autor

**Diego Benicio**

* [LinkedIn](https://www.linkedin.com/in/diego-benicio-65b249277/)

---

## 📍 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
