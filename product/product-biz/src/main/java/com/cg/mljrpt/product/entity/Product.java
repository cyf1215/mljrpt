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
package com.cg.mljrpt.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cg.mljrpt.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Insurance Products
 *
 * @author ifan
 * @date 2023-02-09 10:06:07
 */
@Data
@TableName("product")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Insurance Products")
public class Product extends BaseEntity {

	/**
	 * PRIMARY KEY
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "PRIMARY KEY")
	private Long id;

	/**
	 * Abbreviation
	 */
	@Schema(description = "Abbreviation")
	private String abbreviation;

	/**
	 * Official name
	 */
	@Schema(description = "Official name")
	private String officialName;

	/**
	 * Nickname
	 */
	@Schema(description = "Nickname")
	private String nickname;

	/**
	 * Period
	 */
	@Schema(description = "Period")
	private String period;

	/**
	 * Principal insurance
	 */
	@Schema(description = "Principal insurance")
	private String primaryInsurance;

	/**
	 * Additional insurance 1
	 */
	@Schema(description = "Additional insurance 1")
	private String additionalInsurance1;

	/**
	 * Additional insurance 2
	 */
	@Schema(description = "Additional insurance 2")
	private String additionalInsurance2;

	/**
	 * Additional insurance 3
	 */
	@Schema(description = "Additional insurance 3")
	private String additionalInsurance3;

	/**
	 * Additional insurance 4
	 */
	@Schema(description = "Additional insurance 4")
	private String additionalInsurance4;

	/**
	 * Additional insurance 5
	 */
	@Schema(description = "Additional insurance 5")
	private String additionalInsurance5;

	/**
	 * Additional insurance 6
	 */
	@Schema(description = "Additional insurance 6")
	private String additionalInsurance6;

	/**
	 * Additional insurance 7
	 */
	@Schema(description = "Additional insurance 7")
	private String additionalInsurance7;

	/**
	 * Additional insurance 8
	 */
	@Schema(description = "Additional insurance 8")
	private String additionalInsurance8;

	/**
	 * Additional insurance 9
	 */
	@Schema(description = "Additional insurance 9")
	private String additionalInsurance9;

	/**
	 * Additional insurance 10
	 */
	@Schema(description = "Additional insurance 10")
	private String additionalInsurance10;

	/**
	 * Additional premium 1
	 */
	@Schema(description = "Additional premium 1")
	private Integer additionalPremium1;

	/**
	 * Additional premium 2
	 */
	@Schema(description = "Additional premium 2")
	private Integer additionalPremium2;

	/**
	 * Additional premium 3
	 */
	@Schema(description = "Additional premium 3")
	private Integer additionalPremium3;

	/**
	 * Additional premium 4
	 */
	@Schema(description = "Additional premium 4")
	private Integer additionalPremium4;

	/**
	 * Additional premium 5
	 */
	@Schema(description = "Additional premium 5")
	private Integer additionalPremium5;

	/**
	 * Additional premium 6
	 */
	@Schema(description = "Additional premium 6")
	private Integer additionalPremium6;

	/**
	 * Additional premium 7
	 */
	@Schema(description = "Additional premium 7")
	private Integer additionalPremium7;

	/**
	 * Additional premium 8
	 */
	@Schema(description = "Additional premium 8")
	private Integer additionalPremium8;

	/**
	 * Additional premium 9
	 */
	@Schema(description = "Additional premium 9")
	private Integer additionalPremium9;

	/**
	 * Additional premium 10
	 */
	@Schema(description = "Additional premium 10")
	private Integer additionalPremium10;

}
