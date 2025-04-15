package hu.OpenFishBackend.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // ============= EYE CANDY =============
                // ( ha nem kell, csak töröld/kommenteld ki a dolgokat :pepeOkayge: )
                // =============   begin   =============
                .addServersItem(
                        new Server()
                                .url("http://localhost:8080/")
                                .description("Dev environment url and paths")
                )
//                .addServersItem(
//                        new Server()
//                                .url("https://openfishjs.net/api")
//                                .description("Production environment url and paths")
//                )
                .info(new Info()
                        .title("OpenFishJS API")
                        .description("<osztály ide>, [neveitek ide] csapatának vizsgaremeke")
                        .version("Alpha 0.0.1pc <vagy amit akarsz version-nek>")
                )
                // =============    end    =============
                // ============= EYE CANDY =============
                // swagger-öñ működő jwt-hez szükséges config-ok innen kezdődnek:
                .addSecurityItem(
                        new SecurityRequirement().addList("Bearer Authentication")
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Bearer Authentication",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .bearerFormat("JWT")
                                                .scheme("bearer")
                                )
                );
    }
}
