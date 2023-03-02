package com.cg.mljrpt.illustration.vo;

import com.cg.mljrpt.illustration.template.AbstractTemplate;
import lombok.Data;
import org.thymeleaf.TemplateEngine;

/**
 * @title: Model
 * @projectName Demo
 * @date 2022/5/30 10:30
 */
@Data
public class F4WLModel extends AbstractTemplate {

	public F4WLModel(TemplateEngine engine, String templateName) {
		super(engine, templateName);
	}

	/**
	 * @date: 2022/5/30 10:31
	 * @return:
	 */
	private String productName;

	private String nickName;

	private int age;

	private String gender;

	/**
	 * 先進医療特約
	 */
	private String advancedMedicine;

	/**
	 * 手術総合特約
	 */
	private String operationSec;

	/**
	 * 八疾病延長入院特約
	 */
	private String eightSickness;

	/**
	 * 健康サポート特則
	 */
	private String health;

	/**
	 * 退院後・外来手術通院特約
	 */
	private String goHospital;

	/**
	 * 新三疾病一時金特約
	 */
	private String threeSicknessTemp;

	/**
	 * 三疾病治療月払給付特約
	 */
	private String threeSicknessMonth;

	/**
	 * ガン一時金特約
	 */
	private String cancerTemp;

	/**
	 * ガン治療月払給付特約
	 */
	private String cancerMonth;

	/**
	 * 新三疾病保険料払込免除特約
	 */
	private String threeSicknessPayment;

	/**
	 * 特约个数
	 */
	private int ridersNum;

	/**
	 * 保费合计
	 */
	private String premium;

}