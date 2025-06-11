package com.omnixys.account;

import com.omnixys.account.config.AppProperties;
import com.omnixys.account.config.ApplicationConfig;
import com.omnixys.account.dev.DevConfig;
import com.omnixys.account.utils.Env;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.graphql.data.federation.FederationSchemaFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static com.omnixys.account.util.Banner.TEXT;

@SpringBootApplication(proxyBeanMethods = false)
@Import({ApplicationConfig.class, DevConfig.class})
@EnableConfigurationProperties({AppProperties.class})
@EnableJpaRepositories
@EnableWebSecurity
@EnableMethodSecurity
@EnableAsync
@SuppressWarnings({"ClassUnconnectedToPackage"})
public class AccountApplication {

	public static void main(String[] args) {
		new Env();
		final var app = new SpringApplication(AccountApplication.class);
		app.setBanner((_, _, out) -> out.println(TEXT));
		app.run(args);
	}

	@Bean
	public GraphQlSourceBuilderCustomizer graphQlSourceCustomizer(FederationSchemaFactory factory) {
		return builder -> builder.schemaFactory(factory::createGraphQLSchema);
	}

	@Bean
	FederationSchemaFactory federationSchemaFactory() {
		return new FederationSchemaFactory();
	}
}
