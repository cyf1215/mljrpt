package com.cg.mljrpt.illustration;

import com.cg.mljrpt.common.feign.annotation.EnableMljrptFeignClients;
import com.cg.mljrpt.common.security.annotation.EnableMljrptResourceServer;
import com.cg.mljrpt.common.swagger.annotation.EnableMljrptDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Ifan
 * <p>
 * 项目启动类
 */
@EnableMljrptDoc
@EnableMljrptFeignClients
@EnableMljrptResourceServer
@EnableDiscoveryClient
@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
public class IllustrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IllustrationApplication.class, args);
	}

}
