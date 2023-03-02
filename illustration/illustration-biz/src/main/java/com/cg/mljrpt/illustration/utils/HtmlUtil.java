package com.cg.mljrpt.illustration.utils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;

/**
 * @title: HtmlUtil
 * @projectName Demo
 * @description: Html转Pdf工具类（直接输出到response当中，可直接下载）
 * @date 2022/5/30 10:17
 */
@Slf4j
public class HtmlUtil {

	private HtmlUtil() {
	}

	// 字体路径，放在资源目录下
	// private static final String FONT_PATH = "/etc/ftcloud/public/font/simsun.ttc";

	private static String BASEURI = "illustration/illustration-biz/src/main/resources/pdfTemplate/";

	private static final String[] FONTS = {
			"illustration/illustration-biz/src/main/resources/font/NotoSans-Regular.ttf",
			"illustration/illustration-biz/src/main/resources/font/NotoSans-Bold.ttf",
			"illustration/illustration-biz/src/main/resources/font/NotoSansCJKsc-Regular.otf",
			"illustration/illustration-biz/src/main/resources/font/NotoSansCJKjp-Regular.otf",
			"illustration/illustration-biz/src/main/resources/font/NotoSansCJKkr-Regular.otf",
			"illustration/illustration-biz/src/main/resources/font/NotoNaskhArabic-Regular.ttf",
			"illustration/illustration-biz/src/main/resources/font/NotoSansHebrew-Regular.ttf" };

	public static void htmlToPdf(String html, HttpServletResponse response, String fileName) {
		try {
			// 如果没换名称就以当前时间当做文件名
			String name = StringUtils.isBlank(fileName) ? LocalDateTime.now() + ".pdf" : fileName;
			OutputStream out = response.getOutputStream();
			response.setCharacterEncoding("utf-8");
			response.setHeader("content-type", "application/octet-stream");
			fileName = URLEncoder.encode(name, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

			// ITextRenderer renderer = new ITextRenderer();
			// renderer.setDocumentFromString(html);
			// // 解决中文支持
			// //ITextFontResolver fontResolver = renderer.getFontResolver();
			// //fontResolver.addFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// renderer.layout();
			// renderer.createPDF(out);

			ConverterProperties properties = new ConverterProperties();
			properties.setBaseUri(BASEURI);

			FontProvider fontProvider = new DefaultFontProvider(false, false, false);
			for (String font : FONTS) {
				FontProgram fontProgram = FontProgramFactory.createFont(font);
				fontProvider.addFont(fontProgram);
			}
			properties.setFontProvider(fontProvider);

			HtmlConverter.convertToPdf(html, out, properties);

			out.flush();
			out.close();
		}
		catch (Exception e) {
			log.error("-------HTML转pdf失败--------:{}", e);
		}
	}

}
