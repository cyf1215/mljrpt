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
package com.cg.mljrpt.illustration.service.impl;

import com.cg.mljrpt.illustration.dto.IllustrationDto;
import com.cg.mljrpt.illustration.service.IllustrationService;
import com.cg.mljrpt.illustration.vo.F4WLModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Illustration Service
 *
 * @author ifan
 * @date 2023-02-09 10:06:07
 */
@Service
public class IllustrationServiceImpl implements IllustrationService {

	@Autowired
	private TemplateEngine engine;

	/**
	 * 根据Dto获得F4WLModel
	 * @param illustrationDto
	 * @return F4WLModel
	 * @throws Exception
	 */
	@Override
	public F4WLModel getF4WLModel(IllustrationDto illustrationDto) throws Exception {

		String gender = illustrationDto.getGender();

		// 初始化Model，第二个参数表示要使用的html模板名称（不用加.html后缀）
		F4WLModel model = new F4WLModel(engine, "F4WL");
		// 设定商品名
		model.setProductName("終身医療保障保険 test");
		// 设定商品昵称
		model.setNickName("マイ フレキシィ");
		// 计算并设定年龄
		model.setAge(getAge(illustrationDto.getBirthDate()));
		// 设定性别
		if ("0".equals(illustrationDto.getGender())) {
			model.setGender("Male");
		}
		else {
			model.setGender("Female");
		}

		// 设置特约 等
		this.setRiders(illustrationDto, model);

		return model;
	}

	private void setRiders(IllustrationDto illustrationDto, F4WLModel model) {
		List<String> productRiders = illustrationDto.getProductRiders();

		int ridersNum = 0;
		long premium = 865;

		for (int i = 0; i < productRiders.size(); i++) {
			String productRider = productRiders.get(i);

			switch (productRider) {
				case "advancedMedicine":
					model.setAdvancedMedicine("selected");
					ridersNum++;
					premium = premium + 114;
					break;
				case "operationSec":
					model.setOperationSec("selected");
					ridersNum++;
					premium = premium + 489;
					break;
				case "eightSickness":
					model.setEightSickness("selected");
					ridersNum++;
					premium = premium + 310;
					break;
				case "health":
					model.setHealth("selected");
					ridersNum++;
					premium = premium + 1240;
					break;
				case "goHospital":
					model.setGoHospital("selected");
					ridersNum++;
					premium = premium + 530;
					break;
				case "threeSicknessTemp":
					model.setThreeSicknessTemp("selected");
					ridersNum++;
					premium = premium + 3920;
					break;
				case "threeSicknessMonth":
					model.setThreeSicknessMonth("selected");
					ridersNum++;
					premium = premium + 1530;
					break;
				case "cancerTemp":
					model.setCancerTemp("selected");
					ridersNum++;
					premium = premium + 2260;
					break;
				case "cancerMonth":
					model.setCancerMonth("selected");
					ridersNum++;
					premium = premium + 1180;
					break;
				case "threeSicknessPayment":
					model.setThreeSicknessPayment("selected");
					ridersNum++;
					premium = premium + 1725;
					break;
			}
		}

		model.setRidersNum(ridersNum);
		model.setPremium(NumberFormat.getNumberInstance().format(premium));
	}

	/**
	 * 根据生日获取一个人的年龄
	 * @param strDate
	 * @return age
	 * @throws Exception
	 */
	private int getAge(String strDate) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDay = sdf.parse(strDate);

		Calendar cal = Calendar.getInstance();
		// 出生日期晚于当前时间，无法计算
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		// 当前年份
		int yearNow = cal.get(Calendar.YEAR);
		// 当前月份
		int monthNow = cal.get(Calendar.MONTH);
		// 当前日期
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		// 计算整岁数
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// 当前日期在生日之前，年龄减一
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			}
			else {
				age--;// 当前月份在生日之前，年龄减一
			}
		}
		return age;
	}

}
