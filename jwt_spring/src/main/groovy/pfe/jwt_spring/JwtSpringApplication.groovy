package pfe.jwt_spring

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync
import pfe.jwt_spring.Role.Role
import pfe.jwt_spring.Role.RoleRepsitory

@EnableJpaAuditing
@SpringBootApplication
@EnableAsync

class JwtSpringApplication {

	static void main(String[] args) {
		SpringApplication.run(JwtSpringApplication, args)}
@Bean
		public CommandLineRunner runner(RoleRepsitory roleRepsitory){
	return (args) -> {
		if (roleRepsitory.findByName("USER").isEmpty()) {
			roleRepsitory.save(
					Role.builder().name("USER").build()
			)
		}
	}
}

	}
