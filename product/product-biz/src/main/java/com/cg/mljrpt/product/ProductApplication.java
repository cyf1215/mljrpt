package com.cg.mljrpt.product;

import com.cg.mljrpt.common.feign.annotation.EnableMljrptFeignClients;
import com.cg.mljrpt.common.security.annotation.EnableMljrptResourceServer;
import com.cg.mljrpt.common.swagger.annotation.EnableMljrptDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author pig archetype
 * <p>
 * 项目启动类
 */
@EnableMljrptDoc
@EnableMljrptFeignClients
@EnableMljrptResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
