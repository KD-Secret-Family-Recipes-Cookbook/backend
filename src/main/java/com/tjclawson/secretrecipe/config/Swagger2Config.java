package com.tjclawson.secretrecipe.config;

import com.fasterxml.classmate.TypeResolver;
import com.tjclawson.secretrecipe.models.ErrorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    @Autowired
    TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.tjclawson"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiEndPointsInfo())
                .pathMapping("/")
                .additionalModels(typeResolver.resolve(ErrorDetail.class))
                .ignoredParameterTypes()
                .tags(new Tag("LogoutEndpoint", "Endpoint for logging out a user"));

    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Secret Family Recipe")
                .description("Save your secret family recipes")
                .version(("1.0.0"))
                .build();
    }
}
