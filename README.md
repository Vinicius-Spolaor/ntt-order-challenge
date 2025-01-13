# Order Project (ntt-order-challenge)
Teste prático antes da entrevista técnica.

## Propósito da Aplicação
A aplicação **Order** é responsável por gerenciar pedidos recebidos de forma assíncrona, calculando o valor total dos produtos e garantindo alta disponibilidade e consistência dos dados. A solução integra-se com RabbitMQ para processamento assíncrono, MongoDB para persistência e Redis para evitar duplicação de pedidos em cenários de alta volumetria.

---

## Tecnologias Utilizadas
- **Spring Boot**: Framework principal para desenvolvimento da aplicação.
- **RabbitMQ**: Broker de mensagens para garantir processamento assíncrono.
- **MongoDB**: Banco de dados utilizado para persistência dos pedidos.
- **Redis**: Cache para controle de duplicação e consistência de dados.
- **SpringDoc OpenAPI**: Geração automática da documentação da API com Swagger.

---

## Serviços Necessários

### 1. RabbitMQ
Certifique-se de que o RabbitMQ está em execução localmente ou em um servidor acessível. Por padrão, a aplicação espera que o RabbitMQ esteja disponível em `localhost` na porta `5672`.

#### Comandos para subir o RabbitMQ com Docker:
```bash
docker run -d --hostname rabbitmq --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
```
- Painel de gerenciamento: [http://localhost:15672](http://localhost:15672)
- Usuário padrão: `guest`
- Senha padrão: `guest`

### 2. MongoDB
Certifique-se de que o MongoDB está em execução localmente ou em um servidor acessível. A aplicação conecta-se ao MongoDB utilizando a URI: `mongodb://localhost:27017/orderservice`.

#### Comandos para subir o MongoDB com Docker:
```bash
docker run -d --name mongodb -p 27017:27017 mongo
```

### 3. Redis
Certifique-se de que o Redis está em execução localmente ou em um servidor acessível. A aplicação utiliza o Redis para armazenamento temporário de identificadores de pedidos.

#### Comandos para subir o Redis com Docker:
```bash
docker run -d --name redis -p 6379:6379 redis
```

---

## Como Executar o Projeto

1. Clone o repositório do projeto.
2. Certifique-se de que RabbitMQ, MongoDB e Redis estão em execução.
3. Configure o arquivo `application.properties` ou utilize as configurações padrão:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/orderservice
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.redis.host=localhost
spring.redis.port=6379
```

4. Execute a aplicação:
```bash
mvn spring-boot:run
```

5. Acesse a documentação da API gerada pelo Swagger em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Estrutura da API

### Endpoint: Receber o status de uma ordem
```http
GET /api/orders/status/{orderId}
```
- **Descrição**: Retorna o status de uma ordem e os produtos associados.
- **Resposta**:
```json
{
  "orderId": "12345",
  "products": [
    {
      "id": 1,
      "value": 100.50,
      "quantity": 2
    }
  ],
  "status": "RECEIVED"
}
```

---

## Exemplo de Mensagem para a Fila do RabbitMQ
Para enviar uma mensagem para a fila configurada (`orderQueue`), utilize o seguinte formato:

```json
{
  "orderId": "12345",
  "products": [
    {
      "id": 1,
      "value": 100.50,
      "quantity": 2
    },
    {
      "id": 2,
      "value": 50.00,
      "quantity": 1
    }
  ]
}
```

A mensagem deve ser publicada na fila `orderQueue`. Você pode fazer isso utilizando o painel de gerenciamento do RabbitMQ ou via um cliente HTTP, como o `Postman`, ou utilizando uma ferramenta como o `amqp-publish`.

---

## Futuras implementações (???)
- **Autenticação JWT**: Certificando que autenticações na aplicação serão seguras.
- **Testes unitários**: Visando garantir total funcionamento do código e seguimento de regras de negócio, mesmo com futuras implementações.
- **Circuit Breakers**: Certificando que em situações de falha, não haverá "spam" de requisições para o banco e/ou outros serviços externos.
- **Cloud**: Visando aumentar a escalabilidade da aplicação (com isto, também adicionaremos API Gateway + Balanceadores de Carga para assim distribuir requisições diretamente entre instâncias do serviço sem intermediários).


