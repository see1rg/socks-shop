## Технологии и инструменты

![Spring](https://img.shields.io/badge/-Spring-success?style=flat-square&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/-PostgreSQL-blue?style=flat-square&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/-Docker-informational?style=flat-square&logo=docker&logoColor=white)
![Liquibase](https://img.shields.io/badge/-Liquibase-blueviolet?style=flat-square&logo=liquibase&logoColor=white)
![Lombok](https://img.shields.io/badge/-Lombok-orange?style=flat-square&logo=lombok&logoColor=white)
![Hibernate](https://img.shields.io/badge/-Hibernate-red?style=flat-square&logo=hibernate&logoColor=white)
![Maven](https://img.shields.io/badge/-Maven-orange?style=flat-square&logo=apache-maven&logoColor=white)

### Socks Shop API 

Этот репозиторий содержит исходный код для API интернет-магазина носков, предоставляющего эндпоинты для управления их запасами.

## Таблица базы данных
Класс Socks будет сохранен в таблице базы данных, название которой задается аннотацией @Table(name = "socks"). В этой таблице будут храниться данные о носках, соответствующие полям класса.

## Описание кода

API интернет-магазина носков предоставляет следующие возможности:
Контроллер `SocksController` предоставляет REST API для обработки запросов связанных с носками, а бизнес-логика по обработке запросов реализуется в `SocksService`.


Мой проект содержит интерфейс-репозиторий `SocksRepository`, который расширяет `JpaRepository` для работы с сущностью `Socks` в базе данных. В данном репозитории определены несколько методов с использованием `@Query`, которые позволяют выполнять определенные SQL-запросы к базе данных.

### Методы запросов

1. `getTotalSocksWithCottonPartMoreThanAndColorEqual`

   ```java
   @Query(value = "SELECT SUM(s.quantity) FROM socks s WHERE s.cotton_part > :cottonPart AND s.color = :color", nativeQuery = true)
   Integer getTotalSocksWithCottonPartMoreThanAndColorEqual(@Param("cottonPart") int cottonPart, @Param("color") String color);

Этот метод возвращает сумму количества носков, у которых cotton_part больше заданного значения cottonPart и color равен заданному значению color.

2. `getTotalSocksWithCottonPartLessThanAndColorEqual`

   ```java
   @Query(value = "SELECT SUM(s.quantity) FROM socks s WHERE s.cotton_part < :cottonPart AND s.color = :color", nativeQuery = true)
   Integer getTotalSocksWithCottonPartLessThanAndColorEqual(@Param("cottonPart") int cottonPart, @Param("color") String color);

Этот метод возвращает сумму количества носков, у которых cotton_part равен заданному значениям cottonPart и color равен заданному значению color.

3. `getListOfSocksWithCottonPartEqualAndColorEqual`

   ```java
   @Query(value = "SELECT * FROM socks WHERE color = :color AND cotton_part = :cottonPart", nativeQuery = true)
   Socks getListOfSocksWithCottonPartEqualAndColorEqual(String color, int cottonPart);

Этот метод возвращает список носков, у которых cotton_part и color равны заданным значениям cottonPart и color.

Примечание: Все методы помечены аннотацией @Query, которая позволяет определить собственные SQL-запросы вместо использования стандартных методов Spring Data JPA.

1. **Добавление носков:** Эндпоинт для добавления новых носков на склад.

    - Метод: POST
    - URL: `/api/socks/income`
    - Тело запроса: JSON-представление модели `Socks` с атрибутами, такими как цвет, процент хлопка, количество пар носков и т.д.
    - Результаты:
        - HTTP 200 — удалось добавить приход;
        - HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
        - HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).

2. **Удаление носков:** Эндпоинт для удаления носков со склада.

    - Метод: POST
    - URL: `/api/socks/outcome`
    - Тело запроса: JSON-представление модели `Socks` с атрибутами, такими как цвет, процент хлопка, количество пар носков и т.д.
    - Результаты:
        - HTTP 200 — удалось зарегистрировать отпуск носков;
        - HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
        - HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).

3. **Получение общего количества носков по критериям:** Эндпоинт для получения общего количества носков на складе, соответствующих переданным в параметрах критериям запроса.

    - Метод: GET
    - URL: `/api/socks`
    - Параметры запроса:
        - `color`: Цвет носков для фильтрации (строка).
        - `operation`: Оператор сравнения значения процента хлопка в составе носков (строка). Допустимые значения: "moreThan", "lessThan", "equal".
        - `cottonPart`: Значение процента хлопка в составе носков для сравнения (целое число).
    - Результаты:
        - HTTP 200 — запрос выполнен, результат в теле ответа в виде строкового представления целого числа;
        - HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
        - HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).

**Использованные зависимости**

1. `spring-boot-starter-data-jpa`: Зависимость для использования Spring Boot Data JPA - интеграции Spring Boot с JPA (Java Persistence API) для работы с базами данных через объектно-реляционное отображение (ORM).

2. `spring-boot-starter-web`: Зависимость для использования Spring Boot Web - интеграции Spring Boot с веб-фреймворком для создания RESTful API и обработки HTTP-запросов.

3. `snakeyaml`: Зависимость для использования SnakeYAML - библиотеки для работы с форматом YAML.

4. `liquibase-core`: Зависимость для использования Liquibase - инструмента для управления схемой базы данных.

5. `postgresql`: Зависимость для использования PostgreSQL - базы данных PostgreSQL в качестве зависимости времени выполнения (runtime).

6. `lombok`: Зависимость для использования Project Lombok - инструмента, который позволяет упростить создание Java-классов, автоматически генерируя шаблонный код.

7. `spring-boot-starter-validation`: Зависимость для использования Spring Boot Validation - интеграции Spring Boot с валидацией данных.

8. `hibernate-validator`: Зависимость для использования Hibernate Validator - реализации спецификации Bean Validation для проверки и валидации данных.

**Запуск**

Чтобы использовать API интернет-магазина носков, выполните следующие шаги:

1. Убедитесь, что у вас установлен Docker на вашем компьютере.
2. Клонируйте репозиторий: git clone https://github.com/see1rg/ForJob.git
3. Перейдите в директорию проекта: cd ForJob
4. Соберите Docker-образ: docker build -t socks-api .
5. Запустите контейнер: docker run -p 8080:8080 socks-api
6. API будет доступно по адресу http://localhost:8080/api/socks.
