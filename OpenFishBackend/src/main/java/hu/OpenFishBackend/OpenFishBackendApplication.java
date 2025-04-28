package hu.OpenFishBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenFishBackendApplication {

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
        SpringApplication.run(OpenFishBackendApplication.class, args);
    }

}
