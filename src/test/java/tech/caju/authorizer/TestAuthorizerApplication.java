package tech.caju.authorizer;

import org.springframework.boot.SpringApplication;

public class TestAuthorizerApplication {

	public static void main(String[] args) {
		SpringApplication.from(AuthorizerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
