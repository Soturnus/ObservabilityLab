# TaskManager

Uma aplicação simples de gerenciamento de tarefas construída com Spring Boot, seguindo os princípios da Arquitetura Hexagonal.

## Visão Geral da Arquitetura

A aplicação é estruturada em camadas conforme a Arquitetura Hexagonal:

- **Domain**: Contém as entidades de negócio, interfaces de repositório e serviços de domínio.
- **Application**: Contém os casos de uso (use cases) que orquestram a lógica de aplicação.
- **Infrastructure**: Contém a implementação dos repositórios, mapeadores e configurações de infraestrutura (JPA).
- **Adapter**: Contém os adaptadores de entrada, como controladores REST.

## Diagrama de Classes

```mermaid
classDiagram
    class Task {
        -UUID id
        -String title
        -boolean completed
        +Task(String title)
        +Task(UUID id, String title, boolean completed)
        +complete()
        +getId() UUID
        +getTitle() String
        +isCompleted() boolean
    }
    class TaskRepository {
        <<interface>>
        +save(Task) Task
        +findAll() List~Task~
    }
    class TaskService {
        +createTask(String) Task
    }
    class CreateTaskUseCase {
        -TaskService taskService
        -TaskRepository taskRepository
        +execute(String) Task
    }
    class GetAllTasksUseCase {
        -TaskRepository taskRepository
        +execute() List~Task~
    }
    class JpaTask {
        -UUID id
        -String title
        -boolean completed
    }
    class JpaTaskRepository {
        <<interface>>
        +save(JpaTask) JpaTask
        +findAll() List~JpaTask~
    }
    class TaskMapper {
        +toJpa(Task) JpaTask
        +toDomain(JpaTask) Task
    }
    class TaskRepositoryImpl {
        -JpaTaskRepository jpaTaskRepository
        +save(Task) Task
        +findAll() List~Task~
    }
    class TaskController {
        -CreateTaskUseCase createTaskUseCase
        -GetAllTasksUseCase getAllTasksUseCase
        +createTask(CreateTaskRequest) ResponseEntity~TaskResponse~
        +getAllTasks() ResponseEntity~List~TaskResponse~~
    }
    class CreateTaskRequest {
        -String title
        +getTitle() String
        +setTitle(String)
    }
    class TaskResponse {
        -String id
        -String title
        -boolean completed
        +TaskResponse(String, String, boolean)
        +getId() String
        +getTitle() String
        +isCompleted() boolean
    }
    TaskController --> CreateTaskUseCase
    TaskController --> GetAllTasksUseCase
    CreateTaskUseCase --> TaskService
    CreateTaskUseCase --> TaskRepository
    GetAllTasksUseCase --> TaskRepository
    TaskRepositoryImpl ..|> TaskRepository
    TaskRepositoryImpl --> JpaTaskRepository
    TaskRepositoryImpl --> TaskMapper
    TaskMapper --> Task
    TaskMapper --> JpaTask
    JpaTaskRepository --> JpaTask
```

## Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven

### Passos
1. Clone o repositório.
2. Navegue para o diretório do projeto.
3. Execute `mvn spring-boot:run` ou `java -jar target/TaskManager-0.0.1-SNAPSHOT.jar`.

A aplicação será iniciada na porta 8080.

## Endpoints da API

- **POST /tasks**: Cria uma nova tarefa.
  - Corpo da requisição: `{"title": "Título da tarefa"}`
  - Resposta: Detalhes da tarefa criada.

- **GET /tasks**: Retorna todas as tarefas.
  - Resposta: Lista de tarefas.

## Tecnologias Utilizadas
- Spring Boot
- Spring Data JPA
- H2 Database (ou configure outro no application.yml)
- Maven

## Estrutura de Pastas

A estrutura de pastas segue os princípios da Arquitetura Hexagonal, organizando o código em camadas distintas:

```
TaskManager/
├── docker-compose.yml
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src/
└──├── main/
   │   ├── java/
   │   │   └── com/
   │   │       └── soturno/
   │   │           └── TaskManager/
   │   │               ├── TaskManagerApplication.java
   │   │               ├── adapter/
   │   │               │   └── in/
   │   │               │       └── task/
   │   │               │           └── TaskController.java
   │   │               ├── application/
   │   │               │   └── task/
   │   │               │       ├── CreateTaskUseCase.java
   │   │               │       └── GetAllTasksUseCase.java
   │   │               ├── domain/
   │   │               │   └── task/
   │   │               │       ├── Task.java
   │   │               │       ├── TaskRepository.java
   │   │               │       └── TaskService.java
   │   │               └── infrastructure/
   │   │                   ├── config/
   │   │                   ├── exception/
   │   │                   └── task/
   │   │                       ├── JpaTask.java
   │   │                       ├── JpaTaskRepository.java
   │   │                       ├── TaskMapper.java
   │   │                       └── TaskRepositoryImpl.java
   │   └── resources/
   │       └── application.yml
   └── test/
       └── java/
           └── com/
               └── soturno/
                   └── TaskManager/
                       └── TaskManagerApplicationTests.java

```
