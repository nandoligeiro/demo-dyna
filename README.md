# Demo Dynatrace + Kafka

Este projeto demonstra a integração entre Spring Boot, Apache Kafka e Dynatrace para monitoramento de aplicações.

## Pré-requisitos

- Docker
- Docker Compose
- Java 17
- Maven

## Configuração

1. Clone o repositório:
```bash
git clone https://github.com/nandoligeiro/demo-dyna.git
cd demo-dyna
```

2. Configure as variáveis de ambiente:
```bash
cp .env.example .env
```

3. Edite o arquivo `.env` e adicione suas credenciais do Dynatrace:
```
DYNATRACE_API_URL=sua-url-do-dynatrace
DYNATRACE_TOKEN=seu-token-do-dynatrace
```

## Executando o Projeto

1. Build do projeto:
```bash
./mvnw clean package -DskipTests
```

2. Iniciando os containers:
```bash
docker-compose up -d
```

3. Verificando os serviços:
- Spring Boot API: http://localhost:8080
- Kafdrop (Visualizador Kafka): http://localhost:9000

## Testando a Aplicação

1. Enviando uma mensagem para o Kafka:
```bash
curl -X POST http://localhost:8080/invoices
```

2. Visualizando as mensagens:
- Acesse http://localhost:9000
- Clique no tópico "invoice-events"
- Visualize as mensagens enviadas

## Arquitetura

O projeto consiste em:
- **Spring Boot API**: Aplicação Java que produz mensagens para o Kafka
- **Apache Kafka**: Message broker para processamento de eventos
- **Kafdrop**: Interface web para visualização dos tópicos e mensagens do Kafka
- **Dynatrace OneAgent**: Monitoramento e observabilidade da aplicação

## Estrutura dos Containers

- **invoice-service**: API Spring Boot (porta 8080)
- **kafka**: Apache Kafka broker (porta 9092)
- **zookeeper**: Necessário para o Kafka
- **kafdrop**: UI para Kafka (porta 9000)
- **oneagent**: Dynatrace OneAgent para monitoramento

## Monitoramento

O Dynatrace OneAgent está configurado para monitorar:
- Performance da aplicação Spring Boot
- Métricas do Kafka
- Rastreamento de transações
- Métricas de infraestrutura

## Troubleshooting

1. Se o Dynatrace não iniciar:
   - Verifique as credenciais no arquivo `.env`
   - A aplicação continuará funcionando sem o monitoramento

2. Se o Kafka não conectar:
   - Verifique se o Zookeeper está rodando
   - Verifique os logs com `docker-compose logs kafka`

## Comandos Úteis

```bash
# Ver logs dos containers
docker-compose logs -f

# Reiniciar todos os serviços
docker-compose restart

# Parar todos os serviços
docker-compose down

# Build e restart do invoice-service
docker-compose up -d --build invoice-service
```

## Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'feat: adicionando nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT.
