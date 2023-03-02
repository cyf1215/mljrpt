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

package com.cg.mljrpt.product.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.mljrpt.common.core.util.R;
import com.cg.mljrpt.common.log.annotation.SysLog;
import com.cg.mljrpt.product.entity.Product;
import com.cg.mljrpt.product.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * Insurance Products
 *
 * @author ifan
 * @date 2023-02-09 10:06:07
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "Insurance Products Management")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ProductController {

	private final ProductService productService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param product Insurance Products
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('product_product_get')")
	public R getProductPage(Page page, Product product) {
		return R.ok(productService.page(page, Wrappers.query(product)));
	}

	/**
	 * 通过id查询Insurance Products
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('product_product_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(productService.getById(id));
	}

	/**
	 * 新增Insurance Products
	 * @param product Insurance Products
	 * @return R
	 */
	@Operation(summary = "Add Insurance Products", description = "Add Insurance Products")
	@SysLog("Add Insurance Products")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('product_product_add')")
	public R save(@RequestBody Product product) {
		return R.ok(productService.save(product));
	}

	/**
	 * 修改Insurance Products
	 * @param product Insurance Products
	 * @return R
	 */
	@Operation(summary = "Update Insurance Products", description = "Update Insurance Products")
	@SysLog("Update Insurance Products")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('product_product_edit')")
	public R updateById(@RequestBody Product product) {
		return R.ok(productService.updateById(product));
	}

	/**
	 * 通过id删除Insurance Products
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id删除Insurance Products", description = "通过id删除Insurance Products")
	@SysLog("通过id删除Insurance Products")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('product_product_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(productService.removeById(id));
	}

}
