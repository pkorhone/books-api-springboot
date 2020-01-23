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
      ddl-auto: validate

      # NOTE: if connecting to a new database instance, you can set ddl-auto: create
      # This will connect to your database at runtime and:
      # 1) drop all existing tables 
      # 2) create all tables and relations based on the hibernate entity classes
      # After running once, you can then set ddl-auto: validate 
      # (validate will check if existing tables correspond to hibernate schema, but will not make changes to the database.)
```

### Database schema:

![](https://github.com/pkorhone/books-api-springboot/blob/master/db_schema.png)
