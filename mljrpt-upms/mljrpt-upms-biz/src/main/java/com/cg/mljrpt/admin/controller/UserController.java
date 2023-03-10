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

package com.cg.mljrpt.admin.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cg.mljrpt.admin.api.dto.UserDTO;
import com.cg.mljrpt.admin.api.dto.UserInfo;
import com.cg.mljrpt.admin.api.entity.SysUser;
import com.cg.mljrpt.admin.api.vo.UserExcelVO;
import com.cg.mljrpt.admin.api.vo.UserInfoVO;
import com.cg.mljrpt.admin.api.vo.UserVO;
import com.cg.mljrpt.admin.service.SysUserService;
import com.cg.mljrpt.common.core.exception.ErrorCodes;
import com.cg.mljrpt.common.core.util.MsgUtils;
import com.cg.mljrpt.common.core.util.R;
import com.cg.mljrpt.common.log.annotation.SysLog;
import com.cg.mljrpt.common.security.annotation.Inner;
import com.cg.mljrpt.common.security.util.SecurityUtils;
import com.cg.mljrpt.common.xss.core.XssCleanIgnore;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "??????????????????")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class UserController {

	private final SysUserService userService;

	/**
	 * ??????????????????????????????
	 * @return ????????????
	 */
	@GetMapping(value = { "/info" })
	public R<UserInfoVO> info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_QUERY_ERROR));
		}
		UserInfo userInfo = userService.getUserInfo(user);
		UserInfoVO vo = new UserInfoVO();
		vo.setSysUser(userInfo.getSysUser());
		vo.setRoles(userInfo.getRoles());
		vo.setPermissions(userInfo.getPermissions());
		return R.ok(vo);
	}

	/**
	 * ??????????????????????????????
	 * @return ????????????
	 */
	@Inner
	@GetMapping("/info/{username}")
	public R<UserInfo> info(@PathVariable String username) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERINFO_EMPTY, username));
		}
		return R.ok(userService.getUserInfo(user));
	}

	/**
	 * ????????????id???????????????????????? id ??????
	 * @param deptIds ??????id ??????
	 * @return ?????? id ??????
	 */
	@Inner
	@GetMapping("/ids")
	public R<List<Long>> listUserIdByDeptIds(@RequestParam("deptIds") Set<Long> deptIds) {
		return R.ok(userService.listUserIdByDeptIds(deptIds));
	}

	/**
	 * ??????ID??????????????????
	 * @param id ID
	 * @return ????????????
	 */
	@GetMapping("/{id:\\d+}")
	public R<UserVO> user(@PathVariable Long id) {
		return R.ok(userService.getUserVoById(id));
	}

	/**
	 * ????????????????????????
	 * @param userDTO ????????????
	 * @return
	 */
	@Inner(false)
	@GetMapping("/check/exsit")
	public R<Boolean> isExsit(UserDTO userDTO) {
		List<SysUser> sysUserList = userService.list(new QueryWrapper<>(userDTO));
		if (CollUtil.isNotEmpty(sysUserList)) {
			return R.ok(Boolean.TRUE, MsgUtils.getMessage(ErrorCodes.SYS_USER_EXISTING));
		}
		return R.ok(Boolean.FALSE);
	}

	/**
	 * ??????????????????
	 * @param id ID
	 * @return R
	 */
	@SysLog("??????????????????")
	@DeleteMapping("/{id:\\d+}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	public R<Boolean> userDel(@PathVariable Long id) {
		SysUser sysUser = userService.getById(id);
		return R.ok(userService.removeUserById(sysUser));
	}

	/**
	 * ????????????
	 * @param userDto ????????????
	 * @return success/false
	 */
	@SysLog("????????????")
	@PostMapping
	@XssCleanIgnore({ "password" })
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public R<Boolean> user(@RequestBody UserDTO userDto) {
		return R.ok(userService.saveUser(userDto));
	}

	/**
	 * ???????????????????????????
	 * @param userDto ????????????
	 * @return R
	 */
	@SysLog("??????????????????")
	@PutMapping
	@XssCleanIgnore({ "password" })
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R<Boolean> updateUser(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUser(userDto);
	}

	/**
	 * ??????????????????
	 * @param page ?????????
	 * @param userDTO ??????????????????
	 * @return ????????????
	 */
	@GetMapping("/page")
	public R<IPage<UserVO>> getUserPage(Page page, UserDTO userDTO) {
		return R.ok(userService.getUserWithRolePage(page, userDTO));
	}

	/**
	 * ????????????????????????
	 * @param userDto userDto
	 * @return success/false
	 */
	@SysLog("??????????????????")
	@PutMapping("/edit")
	@XssCleanIgnore({ "password", "newpassword1" })
	public R<Boolean> updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		userDto.setUsername(SecurityUtils.getUser().getUsername());
		return userService.updateUserInfo(userDto);
	}

	/**
	 * @param username ????????????
	 * @return ????????????????????????
	 */
	@GetMapping("/ancestor/{username}")
	public R<List<SysUser>> listAncestorUsers(@PathVariable String username) {
		return R.ok(userService.listAncestorUsersByUsername(username));
	}

	/**
	 * ??????excel ??????
	 * @param userDTO ????????????
	 * @return
	 */
	@ResponseExcel
	@GetMapping("/export")
	@PreAuthorize("@pms.hasPermission('sys_user_import_export')")
	public List<UserExcelVO> export(UserDTO userDTO) {
		return userService.listUser(userDTO);
	}

	/**
	 * ????????????
	 * @param excelVOList ????????????
	 * @param bindingResult ??????????????????
	 * @return R
	 */
	@PostMapping("/import")
	@PreAuthorize("@pms.hasPermission('sys_user_import_export')")
	public R importUser(@RequestExcel List<UserExcelVO> excelVOList, BindingResult bindingResult) {
		return userService.importUser(excelVOList, bindingResult);
	}

}
