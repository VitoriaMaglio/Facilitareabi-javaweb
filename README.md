# 🧠 FacilitaReabi

O **FacilitaReabi** é um sistema voltado para apoiar pacientes em processo de reabilitação, com foco em reduzir o absenteísmo em **teleconsultas** e promover **inclusão digital**.  
A aplicação permite o **cadastro e gerenciamento de pacientes, consultas e usuários**, além de oferecer funcionalidades para agendamento, remarcação e cancelamento de consultas, garantindo que pacientes em situação de vulnerabilidade recebam o suporte necessário.

---

## 🎯 Objetivo e Escopo

O projeto tem como objetivo oferecer uma solução acessível que auxilie **pacientes com dificuldades no uso de tecnologias móveis** a utilizarem a teleconsulta como ferramenta de cuidado digital.  

### O sistema contempla:
- Registro e gerenciamento de **usuários, pacientes e consultas** por meio de endpoints REST.
- **Validação de dados** para evitar cadastros incorretos.
- **Gerenciamento de status de consultas** (agendada, remarcada, cancelada).
- **Registro de motivos de ausência** em consultas.
- **Persistência de dados** em banco relacional utilizando DAO (CRUD).
- **Triagem de aptidão** para verificar se o paciente tem condições mínimas para realizar uma teleconsulta.
- **Envio de notificações** e mensagens explicativas sobre o funcionamento do sistema.
- **Coleta de feedbacks** para aprimoramento contínuo da aplicação.

---

## 🧩 Arquitetura e Camadas

O sistema adota uma **arquitetura em camadas, garantindo separação de responsabilidades e facilidade de manutenção.


### Camadas:
- **Controller** → recebe as requisições REST e direciona o fluxo.
- **Service** → contém as regras de negócio e validações.
- **DTO**  → camada de transferência de dados
- **DAO** → executa as operações CRUD no banco de dados.
- **Model** → representa as entidades principais (Paciente, Consulta, Usuário, FacilitaReabi).
- **Security** → gerencia a criptografia de senhas (classes `Hash` e `BCrypt`).
- **Config** → armazena configurações gerais da aplicação.

---

## ⚙️ Funcionalidades Principais

### 👤 Cadastro de Pacientes
- Inserção de dados básicos: nome, CPF, telefone, e-mail, data de nascimento e vulnerabilidade.
- Validação para evitar cadastros incompletos.
- Endpoints REST para **cadastrar, listar, buscar, atualizar e excluir** pacientes.

### 📅 Cadastro de Consultas
- Associação de consultas a pacientes.
- Registro de data, status e motivo de ausência.
- Endpoints REST para **cadastrar, listar, atualizar e excluir** consultas.

### 👨‍⚕️ Cadastro de Usuários
- Armazenamento de **login e senha criptografada**.
- Endpoints REST para **cadastro, autenticação, listagem, atualização e exclusão** de usuários.

### 💻 Triagem de Aptidão para Teleconsultas
- Verifica se o paciente possui condições mínimas (como ausência de vulnerabilidades graves) para participar de uma teleconsulta.

### 🔁 Remarcação e Cancelamento
- Permite remarcar consultas com registro do motivo da ausência.
- Possibilidade de cancelamento com feedback do paciente.

### 🧾 Instruções e Acessibilidade
- Exibe informações sobre como usar a teleconsulta e recursos de acessibilidade digital.



---

## 🌐 Endpoints REST

| Recurso / Endpoint | Verbo HTTP | Código de Resposta Esperado |
|--------------------|-------------|------------------------------|
| `/usuarios` | POST | 201 Created / 400 Bad Request / 500 Internal Server Error |
| `/usuarios/login` | POST | 201 Created / 400 Bad Request / 500 Internal Server Error |
| `/usuarios` | GET | 200 OK / 500 Internal Server Error |
| `/usuarios/{login}` | GET | 200 OK / 404 Not Found / 500 Internal Server Error |
| `/usuarios/{id}` | PUT | 200 OK / 500 Internal Server Error |
| `/usuarios/{login}` | DELETE | 200 OK / 500 Internal Server Error |
| `/pacientes` | POST | 201 Created / 400 Bad Request / 500 Internal Server Error |
| `/pacientes` | GET | 200 OK / 500 Internal Server Error |
| `/pacientes/{nome}` | GET | 200 OK / 404 Not Found / 500 Internal Server Error |
| `/pacientes/{id_paciente}` | PUT | 200 OK / 500 Internal Server Error |
| `/pacientes/{nome}` | DELETE | 200 OK / 500 Internal Server Error |
| `/consultas` | POST | 201 Created / 400 Bad Request / 500 Internal Server Error |
| `/consultas` | GET | 200 OK / 500 Internal Server Error |
| `/consultas/{id}` | PUT | 200 OK / 500 Internal Server Error |
| `/consultas/{id}` | DELETE | 200 OK / 500 Internal Server Error |

---

## 🧮 Tecnologias Utilizadas

- **Java 17+**
- **Jakarta EE** (para criação dos endpoints REST)
- **DAO Pattern** (para manipulação de dados)
- **BCrypt / Hash** (para criptografia de senhas)
- **MySQL** (banco de dados relacional)
- **Postman / Insomnia** (para testes de API)

---

## 🚀 Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/facilitareabi.git

2. Configure o banco de dados MySQL:
    CREATE DATABASE facilita_reabi;

3. Atualize as credenciais no arquivo de configuração (ex: application.properties).

4. Compile e execute a aplicação
   
5.Acesse os endpoints via Postman em:
  http://localhost:8080

  👩‍💻 Autora
Vitória Valentina Maglio
🎓 Estudante de Análise e Desenvolvimento de Sistemas — FIAP
💡 Foco em Java, APIs REST e soluções para acessibilidade digital
