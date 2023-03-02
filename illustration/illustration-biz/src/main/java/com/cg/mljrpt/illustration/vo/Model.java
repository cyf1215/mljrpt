package com.cg.mljrpt.illustration.vo;

import com.cg.mljrpt.illustration.template.AbstractTemplate;
import lombok.Data;
import org.thymeleaf.TemplateEngine;

import java.util.List;

/**
 * @title: Model
 * @projectName Demo
 * @date 2022/5/30 10:30
 */
@Data
public class Model extends AbstractTemplate {

	public Model(TemplateEngine engine, String templateName) {
		super(engine, templateName);
	}

	/**
	 * @date: 2022/5/30 10:31
	 * @return:
	 */
	private String name;

}