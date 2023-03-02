/*
 * Copyright (c) 2020 mljrpt4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cg.mljrpt.admin;

import com.cg.mljrpt.common.feign.annotation.EnableMljrptFeignClients;
import com.cg.mljrpt.common.security.annotation.EnableMljrptResourceServer;
import com.cg.mljrpt.common.swagger.annotation.EnableMljrptDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lengleng
 * @date 2018年06月21日 用户统一管理系统
 */
@EnableMljrptDoc
@EnableMljrptResourceServer
@EnableMljrptFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MljrptAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MljrptAdminApplication.class, args);
	}

}
