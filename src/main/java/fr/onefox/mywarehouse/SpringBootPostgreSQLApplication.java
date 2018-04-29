package fr.onefox.mywarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SpringBootPostgreSQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPostgreSQLApplication.class, args);
    }
}
