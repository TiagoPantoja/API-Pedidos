# API de Pedidos

API REST para gerenciamento de pedidos utilizando Java, Quarkus e Hibernate como ORM e Swagger para documentação. A API permite criar, listar e buscar pedidos por número de controle.

## Pré-requisitos

- Java 11 ou superior
- Apache Maven 3.6.3 ou superior
- Docker (opcional, para execução com contêiner)

## Banco de Dados

A API utiliza MySQL. Certifique-se de ter um servidor MySQL em execução e crie um banco chamado 'pedidos'.

Para ver os scripts SQL de criação das tabelas 'Pedido' e 'Cliente', verificar o arquivo de texto `SQL` na pasta raiz do projeto.

Configure as propriedades no arquivo `application.properties`:

```
quarkus.datasource.db-kind=mysql
quarkus.datasource.username=root
quarkus.datasource.password=root
quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/pedidos
quarkus.hibernate-orm.database.generation=update
```

## Executando a Aplicação

### Com Maven

1. Clone o repositório do projeto:
```
git clone <URL_DO_REPOSITORIO>
```

2. Navegue até o diretório do projeto:
```
cd <NOME_DO_DIRETORIO>
```

3. Execute a aplicação com o comando:
```
./mvnw quarkus:dev
```

### Com Docker

1. Clone o repositório do projeto:
```
git clone <URL_DO_REPOSITORIO>
```

2. Navegue até o diretório do projeto:
```
cd <NOME_DO_DIRETORIO>
```

3. Compile a aplicação:
```
./mvnw clean package -DskipTests
```

4. Construa a imagem Docker:
```
docker build -t pedidos-api .
```

5. Execute o contêiner Docker:
```
docker run -i --rm -p 8080:8080 pedidos-api
```

## Endpoints

### Criar Pedido

- **URL:** `/pedidos`
- **Método:** POST
- **Descrição:** Cria um novo pedido com os dados fornecidos.
- **Corpo da Requisição:**
```
{
    "numeroControle": "123456",
    "nomeProduto: "Produto 1",
    "valorUnitario": 100.00,
    "quantidade": 5,
    "codigoCliente": 1
}
```

- Resposta de Sucesso:
```
{
    "id": 1,
    "numeroControle": "123456",
    "dataCadastro": "2024-06-07",
    "nome: "Produto 1",
    "valor": 100.00,
    "quantidade": 5,
    "valorTotal": 500,
    "cliente": {
        "id": 1,
        "codigo": 1,
        "nome": "Cliente A",
   } 
}
```

### Listar Pedidos
- **URL:** `/pedidos`
- **Método:** GET
- **Descrição:** Lista todos os pedidos cadastrados.
- **Resposta de Sucesso**:
```
[
    {
        "id": 1,
        "numeroControle": "123456",
        "dataCadastro": "2024-06-07",
        "nome: "Produto A",
        "valor": 100.00,
        "quantidade": 5,
        "valorTotal": 500,
        "cliente": {
            "id": 1,
            "codigo": 1,
            "nome": "Cliente A",
        }
    }
]
```
### Buscar Pedido por Número de Controle
- **URL:** ```/pedidos/{numeroControle}```
- **Método:** GET
- **Descrição**: Retorna um pedido com o número de controle fornecido.
- **Parâmetro de URL**:
- ```**numeroControle**``` - Número de controle do pedido.    
```
{
    "id": 1,
    "numeroControle": "123456",
    "dataCadastro": "2024-06-07",
    "nome: "Produto A",
    "valor": 100.00,
    "quantidade": 5,
    "valorTotal": 500.0,,
    "cliente": {
        "id": 1,
        "codigo": 1,
        "nome": "Cliente A",
    }
}
```

- **Resposta de Erro (404):**
```
{
    "error": "Pedido não encontrado",
}
```

## Documentação da API

A documentação da API está disponível no Swagger UI:
- **URL:** `http://localhost:8080/q/swagger-ui/`

A especificação OpeenAPI pode ser acessada em:
- **URL:** `http://localhost:8080/openapi/`

## Estrutura do Projeto

### Modelos
- **Cliente:** Representa um cliente.

- **Pedido:** Representa um pedido.

### Repositórios    
- **PedidoRepository**: Fornece métodos para interagir com a tabela de pedidos no banco de dados.

### Recursos
- **PedidoResource:** Define os endpoints para criar, listar e buscar pedidos.

### Serviços
- **PedidoService:** Implementa a lógica de negócios para criar, listar e buscar pedidos.

### Testes
- Os testes unitários estão localizados em `src/java/service`. Para executá-los, use o comando:
```
./mvnw test
```
