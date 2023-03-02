/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the mljrpt4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.cg.mljrpt.illustration.controller;

import com.cg.mljrpt.common.core.util.R;
import com.cg.mljrpt.common.security.annotation.Inner;
import com.cg.mljrpt.illustration.dto.IllustrationDto;
import com.cg.mljrpt.illustration.service.IllustrationService;
import com.cg.mljrpt.illustration.vo.F4WLModel;
import com.cg.mljrpt.illustration.vo.Model;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Insurance Products
 *
 * @author ifan
 * @date 2023-02-09 10:06:07
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/illustration")
@Tag(name = "Insurance Products Management")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class IllustrationController {

	@Autowired
	private IllustrationService illustrationService;

	/**
	 * 生成illustration
	 * @param illustrationDto 查询条件
	 * @return
	 */
	@Inner(false)
	@GetMapping("/create")
	public void create(IllustrationDto illustrationDto, HttpServletResponse response) throws Exception {

		F4WLModel model = illustrationService.getF4WLModel(illustrationDto);

		// 生成pdf，指定目标文件路径
		model.parse2Pdf(response, "pdf导出测试.pdf");

		// return R.ok(Boolean.TRUE, "Generated successfully");

	}

}
