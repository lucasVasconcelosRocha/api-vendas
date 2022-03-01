package br.com.lrvasconcelos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MyConfigurations {

    @Bean()
    public CommandLineRunner executar() {
       return args -> {
           System.out.println("rodando configs de dev");
       };
    }

}
