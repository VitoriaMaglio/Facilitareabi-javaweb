JavaWEBChallenge

PROJETO com maven, dependências e tomcat

Começa por controller->Em vez de usar Scanner e System.out, você vai criar endpoints HTTP:



DTO



Define o caminho : /entidade

declara dependência com o servisse

métodos http



post,get,put,delete

(Primeiro tem que se cadastrar no facilitareabi)

Coloca anotação da requisição

Path

@Produces(MediaType....)

método do tipo Response





Service-> Adi8cionar métodos para API



Criando API REST:





&nbsp;http://localhost:8080/{nome-da-aplicacao}/{root-path-do-JAXRS}/{path-da-classe-controller}/{path-do-método}





Códigos de status HTTP



CÓDIGOS DE SUCESSO

200 OK -> requisição é bem sucedida -> GET,PUT,DELETE que retornam dados ou confirmação



201 Creaed -> recurso criado -> insere um novo recurso



204 No Content -> sucesso, mas sem conteúdo no corpo da resposta -> DELETE,PUT que não precisam retornar dados



CÓDIGOS DE ERRO DE CLIENTE

400 Bad Request -> Requisição malformada -> Dados inválidos enviados pelo cliente.



401 Unauthorized -> Não autorizado -> Quando o usuário precisa de autenticação, mas não forneceu credenciais.



403 Forbidden -> Proibido -> Não tem permissão



404 Not found -> Recurso não encontrado -> Tenta busca/excluir recurso que não existe.



Códigos de erro do servidor (Server Error)

500 Internal Server Error -> Erro inesperado no servidor -> 



502 Bad Gateway -> Problema de comunicação entre servidores -> 





503 Service Unavailable -> Serviço indisponível -> Servidor/banco fora do ar 







Correspondência básica com “comandos”



Cliente             verbo             código esperado





Buscar recurso        GET            200 OK / 404 Not Found





Criar recurso	      POST	     201 Created / 400 Bad Request





Atualizar recurso      PUT	     200 OK / 400 Bad Request / 404 Not Found





Excluir recurso	       DELETE	     204 No Content / 404 Not Found





Para ter esses códigos refletidos no front, no return do método, usar 

// Sucesso

return Response.ok(usuarioDTO).build(); // 200 OK

return Response.status(Response.Status.CREATED).entity(usuario).build(); // 201 Created

return Response.noContent().build(); // 204 No Content



// Erro

return Response.status(Response.Status.BAD\_REQUEST).entity("Erro nos dados").build(); // 400

return Response.status(Response.Status.NOT\_FOUND).build(); // 404

return Response.status(Response.Status.INTERNAL\_SERVER\_ERROR).build(); // 500









OBJDTO-> Contém somente os dados que você quer expor

Usar a conversão de entidade para objDTO quando:

\- você envia dados para o front-end

\- você recebe dados do front-end para criar ou atualizar



\- TENHO UM DTOAÇÃO para cada método







Conversão:

No dto fazer um método do tipo objDTO recebendo como parâmetro sua entidade e retornar um novo objDTO setando seus atributos com o parâmetro entidade







CAMINHO PARA CRIAR:



dto

criar um dto com as informações

dtoResponse e dtoRequest

controller

cria um método com requisição http



service

criar o método com a lógica 



VOLTAR TUDO PARA ENTENDER:

Projeto antigo lógica:

Projeto antigo rodava a partir da Main que chamava

Controller->Service->Dao

Dados entravam por meio de scanner e sout e eram armazenados e transmitidos brutos



Lógica para conectar com aplicação web:

Ao invés de receber dados com métodos e scanner, vou criar requisições http, get,post,put,delete para os dados para que o usuário consiga interagir e enviar dados





Passos para conectar:

Primeiro precisamos entender o fluxo do caminho dos dados:

Controller(dependência na camada service)- Service(dependência na camada repository)- Dao(extends JpaRepository) - DTO- Entity



Ter um dto para request e response-> para otimizar o código e não ter que ficar repetindo linhas de código, um dto da ação pode ser chamado. Tem os atributos que o sistema quer passar para o usuário na resposta e o que ele quer que o usuário entre na requisição

Em cada dto ter um método que converte Usuario-DTO e vice-versa 





Service nos temos métodos de validação e compatíveis com as requisições http

//Método com lógica de cadastrar/atualizarlistar/deletar usuário com parâmetro dto

Chamando método correspondente do dao e chamar dto método de conversão

Aqui vamos inserir no banco fazendo a conversão dos dados brutos para os dados da entidade



Controller temos as requisições https com anotações e criação dos endpoints







Tabela de endpoints-> api restful

URIs (caminhos dos recursos)

Verbos HTTP (get,post,put,delete)

código de status esperado









RECOMEÇANDO PROJETO 

Codando Simples youtube



Primeiro criar um projeto com maven archetype 

Depois adicionar plugin e dependencies no pom.xml

Tudo dentro da pasta webapp é visível pelo browser nas a pasta web-inf não.



Servelt -> permite dinamismo em páginas web com java

É um objeto java para gerar uma página html, adicionar dependencie



Criando estrutura do projeto



model

dto

servisse

resouce

dao



dto criar métodos de conversão entre dto e entidade

Converter dto para usuário



public Entidade parâmetro classedto

retornar um novo Usuário(classedto.atributos)



Converter de entidade para dto

public dto parâmetro entidade

retorna novo dto(entidade.getatributos)







com base no fluxo antigo da main criar endpoints



&nbsp;         



| Opção do menu           | Endpoint REST                           | Método HTTP      | Descrição                    | Retorno                 | Código de status |

| ----------------------- | --------------------------------------- | ---------------- | ---------------------------- | ----------------------- | ---------------- |

| 1. Login/cadastro       | `/usuarios/login`         | `POST`           | Login e cadastro de usuário | JSON com mensagem       201 / 400 / intenal server error  |

&nbsp;	----1.1 '/usuarios/pacientes`                                      `POST`            Cadastro de paciente      JSON com mensagem       201 / 400 / intenal server error  |

&nbsp;		1.2 'consultas/'







| 2. Agendar consulta     | `/consultas`   '/cadastroconsulta'

&nbsp;                        | `POST`           | Cria uma nova consulta       | JSON da consulta criada | 201 / 400 / intenal server error  |

 		

| 3. Remarcar/cancelar    | `/consultas/{id}`                       | `PUT` / `DELETE` | Atualiza ou exclui consulta  | Mensagem                | 200 / 204        |



| 4. Atualizar paciente   | `/pacientes/{id}`                       | `PUT`            | Atualiza dados do paciente   | JSON atualizado         | 200              |



| 5. Excluir paciente     | `/pacientes/{id}`                       | `DELETE`         | Remove paciente do sistema   | Mensagem                | 204              |



| 6. Atualizar usuário    | `/usuarios/{id}`                        | `PUT`            | Atualiza dados de login      | JSON atualizado         | 200              |



| 7. Excluir usuário      | `/usuarios/{id}`                        | `DELETE`         | Remove usuário               | Mensagem                | 204              |



| 8. Buscar dados         | `/usuarios`, `/pacientes`, `/consultas` | `GET`            | Lista registros              | Lista JSON              | 200              |



| 9. Médico (listar tudo) | `/medico/dados`                         | `GET`            | Lista pacientes e consultas  | JSON com arrays         | 200              |



| 0. Feedback             | `/feedback`                             | `POST`           | Salva comentário do usuário  | Mensagem                | 201              |







TABELA


UsuarioResource

post 201,400,internal server error
post /login 200, unautorized, internal server error -> autenticar usuário com criptografia



put  `/usuarios/{id}`

delete  `/usuarios/{id}` 


PacienteResource


post path /cadastro/paciente  201,400,internal server error



put `/pacientes/{id}`
delete `/pacientes/{id}`

get '/pacientes'



ConsultaResource

post /cadastro/consulta 201,400,internal server error


put `/consultas/{id}`

delete `/consultas/{id}`

get `/consultas/`









criando métodos http em resource

1- @path da classe
2-métodos verbo http, consumir e produzir json, método tipo Response com parametor dto





1. post de login de usuário pq para usar o site tem q ter login -> cria lógica de chamar service- banco- resposta http
   Faz lógica do serivce e banco

entender fluxo front
Front-end (JSON)

&nbsp;  ↓

Resource (recebe o JSON → UsuarioRequest)

&nbsp;  ↓

Service (regras de negócio)

&nbsp;  ↓

DAO (executa INSERT no banco via JDBC)

&nbsp;  ↓

Banco de dados (usuário é salvo)

buscar login caminho inverso Banco de dados → DAO → Service → Controller → JSON para o front-end










“Quando o usuário faz login, o front envia um JSON para o endpoint /api/usuarios/login.

O controller recebe esse JSON e o transforma em um UsuarioRequest.

O service converte esse DTO em uma entidade Usuario e chama o DAO para inserir no banco via JDBC.

Depois o service busca o usuário cadastrado, converte para UsuarioResponse e retorna para o front.

Assim, temos um fluxo completo de API REST, sem usar frameworks como Spring, apenas Jakarta EE e boas práticas de camadas.”







servisse chama o dao convertendo entre entidade e dto


faz os métodos que tem a "mesma" base lógica

UsuarioLoginDto com método post path /login para autenticar login

