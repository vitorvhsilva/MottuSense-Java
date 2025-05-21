# MottuSense-Java

Vitor Hugo da Silva - 
Brendon de Paula - 
João Henrique Murilla Ganança - 556405

# 🚀 MottuSense - Sistema de Gestão de Usuários e Monitoramento de Motos

Este projeto é um microsserviço que permite o **cadastro de usuários**, a **localização de filiais mais próximas com base no CEP** e o **monitoramento inteligente de motos** através de alertas em tempo real. É parte da solução MottuSense voltada para a gestão e operação de veículos em pátios.

---

## 🧩 Funcionalidades

### 👤 Gestão de Usuários
- Cadastro de novos usuários no banco de dados.
- Consulta de usuários cadastrados.
- Atualização de dados dos usuários.

### 📍 Localização de Filiais
- Localiza filiais mais próximas do usuário com base no seu CEP.
- Utiliza serviços de geolocalização e distância para cálculo.

### ⚠️ Sistema de Alertas de Motos
O sistema emite alertas automáticos baseados no comportamento e status das motos:

- 🔁 **Entrada no pátio**: alerta quando a moto entra em uma filial.
- 🚪 **Saída do pátio**: alerta quando a moto sai de uma filial.
- 🚫 **Sem placa**: alerta se a moto chegar ao pátio sem placa.
- 🛠️ **Manutenção necessária**: alerta se a moto precisa de manutenção.
- ✅ **Pronta para locação/venda**: alerta quando a moto está disponível para ser alugada ou vendida.

### 🔁 Integração com Serviços Externos
Consulta de CEP via API externa (ex: ViaCEP) usando Feign Client.

Preparado para integração com outros serviços externos no ecossistema da aplicação.

---

📌 Exemplos de Alertas Esperados
Tipo de Alerta	Descrição
Entrada no pátio	A moto com ID 1234 entrou na filial SP-01 às 15h.
Saída do pátio	A moto com ID 5678 saiu da filial RJ-02 às 17h.
Moto sem placa	A moto com ID 9876 chegou no pátio sem placa instalada.
Manutenção necessária	A moto com ID 1122 está com 10.000km e precisa de revisão.
Pronta para locação/venda	A moto com ID 3344 está limpa, revisada e disponível.

🔒 Segurança e Autenticação

Validações de entrada com Bean Validation (@Valid).

🚀 Tecnologias Utilizadas

🧰 Spring Boot 3.4.5
Framework principal do projeto, que facilita a criação de aplicações Java baseadas em Spring, com configuração automática e rápida inicialização.

📦 Maven
Sistema de build usado para gerenciamento de dependências e empacotamento do projeto.

🔧 Principais Starters do Spring:
spring-boot-starter-data-jpa
Para integração com JPA/Hibernate, facilitando o acesso e persistência em bancos de dados relacionais.

spring-boot-starter-web
Para criação de APIs RESTful usando Spring MVC.

spring-boot-starter-validation
Para validação de dados via anotações como @NotNull, @Email, etc.

spring-boot-starter-test
Fornece bibliotecas e utilitários para testes unitários e de integração.

spring-boot-devtools
Ferramentas de desenvolvimento com reload automático e suporte a hot swapping (útil para desenvolvimento local).

📍 Geolocalização e Integração
spring-cloud-starter-openfeign
Para facilitar chamadas HTTP para APIs externas (por exemplo, ViaCEP, serviços de mapas etc.) através do cliente declarativo Feign.

🛢️ Bancos de Dados

Oracle JDBC Driver (ojdbc11)
Conector para banco de dados Oracle, usado em ambiente de produção ou homologação.

🧱 Outras Bibliotecas
Lombok (1.18.36)
Reduz a verbosidade no código Java com anotações como @Getter, @Setter, @Builder, entre outras.

ModelMapper (3.0.0)
Para conversão automática entre entidades e DTOs, simplificando o mapeamento de objetos.

💻 Java 21
A versão do Java especificada para compilação e execução do projeto.

![image](https://github.com/user-attachments/assets/b25bd178-9471-423c-aa61-d36bb7294c5e)

📦 Passos para rodar o projeto localmente
1. **Clone o repositório**

git clone https://github.com/vitorvhsilva/MottuSense-Java.git
cd mottusense-users

2. **Configure o banco de dados**
# spring.datasource.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL
# spring.datasource.username=RM556405
# spring.datasource.password=Fiap#2025
# spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
# spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
# #spring.jpa.show-sql=true
# spring.jpa.properties.hibernate.format_sql=true
# spring.jpa.hibernate.ddl-auto=create-drop

3. **Build do projeto**

mvn clean install

4. **Rodar a aplicação**

Rodar o arquivo UsersApplication

5. **Acesse a API**

A aplicação será iniciada em:
👉 http://localhost:8080
