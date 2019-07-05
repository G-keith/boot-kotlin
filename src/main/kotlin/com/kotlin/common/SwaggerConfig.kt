package com.kotlin.common

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.Parameter
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 *@author keith
 *@date 2019/7/5
 *@version 1.0
 */
@Configuration
@EnableSwagger2
class SwaggerConfig{

    @Bean
    fun createRestApi():Docket{
        val ticketPar= ParameterBuilder()
        val pars= mutableListOf<Parameter>()
        ticketPar.name("token").description("添加token参数")
            .modelRef(ModelRef("string")).parameterType("header")
            .required(false).build()
        pars.add(ticketPar.build())
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.kotlin.controller"))
            .build()
            .globalOperationParameters(pars)
            .apiInfo(apiInfo())
    }

    fun apiInfo():ApiInfo{
       return ApiInfoBuilder()
            .title("Gradle构建Kotlin")
           .contact(Contact("Kotlin","https://github.com/G-keith","gm1605763261@gmail.com"))
           .version("1.0")
           .description("Kotlin:文档描述")
           .build()
    }

}