# Book Search Back End (Spring Boot / Maven)

This project was created as a back end for the [Book Search React App](https://github.com/pkorhone/booksearch).

## API Endpoints used by front end:

| Use case | Method | Endpoint | Details | Live examples |
| --- | --- | --- | --- | --- |
| fetch all books from database | GET | `/api/books` | | https://quiet-spire-31652.herokuapp.com/api/books |
| single book by id | GET | `/api/books/{id}` | | https://quiet-spire-31652.herokuapp.com/api/books/8 |
| search for books matching a search term in any attribute | GET | `/api/search?term={searchTerm}` | | https://quiet-spire-31652.herokuapp.com/api/search?term=emperor |
|search for books matching specific details | GET | `/api/detailedsearch?{parameters}` | The available parameters are as follows: <br> - author <br> - title <br> - description <br> - publisher <br> - year <br> Requests may include all, some or none of these parameters in any order. | https://quiet-spire-31652.herokuapp.com/api/detailedsearch?description=economics&title=shape <br><br> https://quiet-spire-31652.herokuapp.com/api/detailedsearch?year=2019&publisher=penguin

## Setup:

After cloning this repo, you can import it to your IDE of choice as a Maven project. After setting up a database connection as described below, you can then simply run the project as a Java application and the server will be running by default at `http://localhost:8080`.

### Database connection

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
```

### NOTE: 
If you are connecting to a new database instance, you can set `ddl-auto: create` in the config file described above.
This will connect to your database at runtime and:
  1) drop all existing tables 
  2) create all tables and relations based on the hibernate entity classes

After running the project once with `ddl-auto: create`, you can set `ddl-auto: validate` again. (The validate option will only check if existing tables correspond to hibernate schema, but will not make changes to the database.)

### Database schema:

![](https://github.com/pkorhone/books-api-springboot/blob/master/db_schema.png)

## Demo data entry

If you have followed the steps above, you now have an empty database. If you want to populate your database with dummy data for testing, you can copy the complete SQL queries from [`DEMO_DATABASE_SQL.txt`](https://github.com/pkorhone/books-api-springboot/blob/master/DEMO_DATABASE_SQL.txt) file located in the root directory.