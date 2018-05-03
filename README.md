# My warehouse
Small tracking application for a warehouse with a REST API

_Realize with Spring Boot, a PostgreSQL database and ❤️._

Imagine that a freight company wishes to have a solution allowing it to make its declarations of movements of goods in its warehouse. These movements (entry and exit of warehouse) will have to be declared to a company which asks to receive the declarations by email, in XML format.

## Configure & Start the application

### Prerequisites
- Java 8 version
- Maven
- PostgreSQL
- Change informations in `application.properties` with your configuration :


```
# ===============================
#          DATA SOURCE
# ===============================
# Set here configurations for the database connection
spring.datasource.url=jdbc:postgresql://localhost:5432/springboot
spring.datasource.username=postgres
spring.datasource.password=

[...]

# ===============================
#       EMAIL CONFIGURATION
# ===============================
# Set here configurations for the emails
spring.mail.host=smtp.gmail.com
spring.mail.from=XXXX@gmail.com
spring.mail.to=XXXX@icloud.com
spring.mail.username=XXXX
spring.mail.password=XXXX
```
### Command
To start the application, it's simply _(normally...)_ :
```
 mvn spring-boot:run
```

## Informations

- I used JAXB for XML export. JAXB is able to serialize Java objects into an XML file, and deserialize them.
- I used to send the emails: Java mail (support in the Spring framework)

## Documentation & Tests
To understand the REST API available, I suggest you consult the documentation generated with Swagger `http://localhost:8080/swagger-ui.html`.
Or go to the base address of the application, default `http://localhost:8080` and choose to consult the documentation.

With this tool, you can send examples to create or retrieve movements in your warehouse.

## Ideas to evolve this project
- *Manage different warehouses* - In this case a single source is possible but with a warehouse management system, it will become possible to manage several provenances.

- *Extract the sending of mails in an asynchronous service* - He would take care of sending in the background. Maybe going to set up a service like Apache ActiveMQ could recover this sending order and call an application in charge of the management of emails. Effective if a failure occurs on the server because better monitoring is in place. **Everything depends of course on the needs of the client ...**

- *Business rule of verification* - Not knowing if a merchandise reference is really unique (even though in theory it should be the case ...). The business rule in charge of verifying that a good is present in the warehouse before its exit makes a count of the elements present with this reference code. If the number of output exceeds the number of entries the request is blocked.

- *Set up a management of users issuing transactions*

- *More unit tests analyzing the sending of emails*

- *Create a custom exception, to avoid using the default spring exception.* - Use I18N to understand return errors in the sending language. 