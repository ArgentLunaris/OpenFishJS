package hu.OpenFishBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Autentikacio0407Application {

    @Bean
    String[] openEndpoints(){
        return new String[]{
            "/",
            "/register",
            "/login",
            "/h2-console/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/checkToken"
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Autentikacio0407Application.class, args);
    }

}
