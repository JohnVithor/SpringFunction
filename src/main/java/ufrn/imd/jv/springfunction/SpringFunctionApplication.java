package ufrn.imd.jv.springfunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFunctionApplication.class, args);
    }

    @Bean
    public Function<Flux<String>, Flux<String>> validatePassword() {
        return flux -> flux.map(value -> {
            if (value.length() < 8) {
                return "Senha do usuario possui menos de 8 caracteres";
            }
            boolean up = false;
            boolean low = false;
            boolean digit = false;
            boolean special = false;
            for (char c : value.toCharArray()) {
                if (Character.isUpperCase(c))
                    up = true;
                else if (Character.isLowerCase(c))
                    low = true;
                else if (Character.isDigit(c))
                    digit = true;
                else
                    special = true;
            }
            if (!up) {
                return "Senha do usuario deve possuir um caractere maiusculo";
            }
            if (!low) {
                return "Senha do usuario deve possuir um caractere minusculo";
            }
            if (!digit) {
                return "Senha do usuario deve possuir um digito";
            }
            if (!special) {
                return "Senha do usuario deve possuir um caractere especial";
            }
            return "OK";
        });
    }
}
