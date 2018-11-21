package com.newbee.net.swagger2;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2自动配置
 * 
 * @author zheng.th
 *
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger2", name = "base-package")
public class Swagger2Config {

	@Autowired
	private Swagger2Properties swagger2Properties;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(Lists.newArrayList(apiKey())).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(swagger2Properties.getApiTitle()).version(swagger2Properties.getVersion())
				.build();
	}

	/**
	 * 添加全局参数
	 * @return
	 */
	private ApiKey apiKey() {
		return new ApiKey("apikey", "Authorization", "header");
	}

}