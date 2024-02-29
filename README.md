# Тестовое задание для Dynamika
Выполнил Михайлов Павел

## Как запустить
- Скачать проект себе
- Запустить docker-compose.yml
- Собрать jar-файл с помощью maven (mvn clean package)
- Запустить файл из папки target через java -jar имя_файла.jar

## Интерфейс
Чтобы попасть на главную страницу, необходимо перейти по ссылке http://localhost:8080/library
Дальнейшая навигация осуществляется через кнопки на сайте.


Также доступна REST-версия через swagger (http://localhost:8080/dynamika)

## Используемые технологии
Java 1.8, Spring Boot 2.7.18, PostgreSQL 14, Liquibase, Lombok, Mapstruct, Spring Data Jpa, Spring Boot Test, Spring MVC, Testcontainers, Swagger, Docker, Thymeleaf
