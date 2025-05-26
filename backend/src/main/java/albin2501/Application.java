package albin2501;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public final static String backendBase = "http://localhost:2501";
    public final static String frontendBase = "http://localhost:1052";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
