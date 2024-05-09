## Описание
Проект представляет собой REST API, разработанное для управления продуктами. Он предоставляет базовые операции CRUD для объектов Product через HTTP запросы.
## Технологии
- Java 17: Язык программирования. 
- Spring Boot: Основной фреймворк для создания приложения.
- Spring MVC: Используется для обработки HTTP запросов.
- JPA/Hibernate: Для взаимодействия с базой данных.
- H2 Database: Используется для хранения данных (in-memory)
- JUnit Jupiter: Используется для написания тестов.
- Log4j2: Используется для логирования приложения.
## Структура объекта
- Model: Класс Product, представляющий продукт.
- Controller: Класс RestApiController для обработки HTTP запросов.
- Service: Класс ProductService для бизнес-логики работы с продуктами.
- Repository: Интерфейс ProductRepository для доступа к данным продуктов.
## Как запустить проект :wrench:
- git clone https://github.com/Airat1997/SberTest
- cd <путь к проекту>
- mvn install
- mvn spring-boot:run
- Альтернативный вариант через IntelliJ IDEA запустить класс Main
## Описание эндпоинтов
- GET /products: Возвращает список всех продуктов.
- ![GET.gif](images%2FGET.gif)
- GET /products/{id}: Возвращает продукт по его идентификатору.
- ![GET_id.gif](images%2FGET_id.gif)
- POST /products: Создает новый продукт.
- ![POST.gif](images%2FPOST.gif)
- PUT /products/{id}: Обновляет существующий продукт или создает новый, если продукт с таким идентификатором не существует.
- ![PUT.gif](images%2FPUT.gif)
- DELETE /products/{id}: Удаляет продукт по его идентификатору.
## P.S
В resources добавил скрипт для заполнения БД при запуске приложения.



