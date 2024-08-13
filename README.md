# Projeto KFCrazy

## Resumo do Projeto

O projeto KFCrazy é uma aplicação para um sistema de autoatendimento para restaurantes fast food. O sistema permite que clientes selecionem, façam pedidos, paguem, acompanhem o status dos pedidos e recebam notificações quando os pedidos estiverem prontos. A aplicação utiliza Spring Boot para o desenvolvimento da API e Docker para a containerização dos serviços.

## Domain-Driven Design (DDD)

O modelo de Domain-Driven Design (DDD) do projeto está disponível no Miro. Por favor, consulte o link fornecido para obter mais detalhes sobre a estrutura e os conceitos utilizados.
URL: https://miro.com/app/board/uXjVKva4Vcc=/

## Instruções para Rodar a Aplicação

Para rodar a aplicação, siga os comandos abaixo:

1. **Faça login no Docker:**
   ```bash
   docker login

2. Crie um volume para o PostgreSQL:
   ```bash
   docker volume create postgres_data

3. Baixe a imagem mais recente do Docker Hub:
     ```bash
   docker pull fabriciofsousa/kfcrazy:latest

4. Construa a imagem do projeto:
   ```bash
   docker build -t fabriciofsousa/kfcrazy:latest .

5. Inicie os serviços com Docker Compose:
   ```bash
   docker compose up

## Contato

**Integrantes do Grupo 58**

- **Fabricio Ferreira Sousa**  
  Email: fabriciof1122@gmail.com





