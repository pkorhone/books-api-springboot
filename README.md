# Book Search Back End (Spring Boot)

## Setup:

This project is set up to use a MySQL database. Before running the project, you must create a file named `application.yaml` to the directory `/src/main/resources` with the following content to set up your database connection:

```
# /src/main/resources/application.yaml

spring:
  datasource:
    url: jdbc:mysql:// {database url} / {schema}
    username: {username}
    password: {password}
  jpa:
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5Dialect
    hibernate:
      ddl-auto: create-drop
```

