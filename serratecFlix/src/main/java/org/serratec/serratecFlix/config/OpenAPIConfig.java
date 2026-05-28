package org.serratec.serratecFlix.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI myOpenAPI(){
       
        Contact contato = new Contact();
        contato.setEmail("grupo3API@gmail.com");
        contato.setName("Grupo 3 - API - Turma 37");
        
        contato.setUrl("https://serratec.org.br");
        
        License apacheLicense = new License()
        .name("Apache licence")
        .url("https://www.apache.org/licenses/LICENSE-2.0");
        
        Info info = new Info()
        .title("SerratecFlix")
        .version("1.0")
        .contact(contato)
        .description("Uma API para cadastrar filmes e séries, usuários, listar favoritos e avaliar filmes e séries.\n\n"
                + "**Equipe:**\n"
                + "- João Pedro Carneiro Motta - joaopedrobr3@gmail.com\n"
                + "- Yan Martins de Oliveira - yanmartinsd3@gmail.com\n"
                + "- Carlos Eduardo Carvalho - carloscarvalho201532@gmail.com\n"
                + "- Marcos Paulo - marcospoliveiramello@gmail.com\n"
                + "- Phelipe Damasio - phelipe1327@gmail.com")
        .termsOfService("https://serratec.org.br")
        .license(apacheLicense);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .description("Insira o token JWT obtido no POST /login");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", securityScheme));
    }
}