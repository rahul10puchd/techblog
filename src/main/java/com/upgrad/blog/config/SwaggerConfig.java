package com.upgrad.blog.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket technicalBlogDocument() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.upgrad.blog.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaDeta());
    }

    public ApiInfo metaDeta()
    {
        ApiInfo info =new ApiInfo(
                "Documentation  for Techblog",
                "API for Author's Info",
                "1.0.1",
                "https://www.upgrad.com/terms",
                "Upgrad Education",
                "Licensed Under Upgard Education",
                "https://upgrad.com"
        );
        return info;
    }
}
