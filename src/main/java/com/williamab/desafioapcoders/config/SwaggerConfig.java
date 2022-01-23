package com.williamab.desafioapcoders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configurações da documentação da API (Swagger).
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Configura um {@link Docket} para a documentação da API com Swagger.
	 * 
	 * @return um {@link Docket}
	 */
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.williamab.desafioapcoders.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	/**
	 * Configura um {@link Contact} com as informações de contato do desenvolvedor
	 * da API.
	 * 
	 * @return um {@link Contact}
	 */
	private Contact contact() {
		return new Contact("William Alberto Bertoldi", "https://github.com/WilliamAB", "william.bertoldi@gmail.com");
	}

	/**
	 * Configura um {@link ApiInfo} com as informações da API.
	 * 
	 * @return um {@link ApiInfo}
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Desafio ApCoders - API de Gestão de Condomínios")
				.description("A API de Gestão de Condomínios permite o cadastro e a visualização de condomínios, unidades, inquilinos, despesas e tipos de despesa.")
				.version("1.0")
				.contact(contact())
				.build();
	}

}
