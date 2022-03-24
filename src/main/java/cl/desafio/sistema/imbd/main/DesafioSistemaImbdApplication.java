package cl.desafio.sistema.imbd.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan({"cl.desafio.sistema.imbd.main.*"})
public class DesafioSistemaImbdApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(DesafioSistemaImbdApplication.class, args);
	}
}
