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

package com.cg.mljrpt.illustration.service;

import com.cg.mljrpt.illustration.dto.IllustrationDto;
import com.cg.mljrpt.illustration.vo.F4WLModel;

/**
 * Illustration Service
 *
 * @author ifan
 * @date 2023-02-09 10:06:07
 */
public interface IllustrationService {

	/**
	 * 获得F4WLModel
	 * @param illustrationDto
	 * @return
	 */
	F4WLModel getF4WLModel(IllustrationDto illustrationDto) throws Exception;

}
