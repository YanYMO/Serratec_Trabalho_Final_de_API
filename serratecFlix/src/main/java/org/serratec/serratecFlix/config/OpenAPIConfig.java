package org.serratec.serratecFlix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


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

        return new OpenAPI().info(info);
    }

    
    
}