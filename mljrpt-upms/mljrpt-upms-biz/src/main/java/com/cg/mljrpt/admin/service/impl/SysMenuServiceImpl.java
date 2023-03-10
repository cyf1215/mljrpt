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

package com.cg.mljrpt.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.mljrpt.admin.api.entity.SysMenu;
import com.cg.mljrpt.admin.api.entity.SysRoleMenu;
import com.cg.mljrpt.admin.mapper.SysMenuMapper;
import com.cg.mljrpt.admin.mapper.SysRoleMenuMapper;
import com.cg.mljrpt.admin.service.SysMenuService;
import com.cg.mljrpt.common.core.constant.CacheConstants;
import com.cg.mljrpt.common.core.constant.CommonConstants;
import com.cg.mljrpt.common.core.constant.enums.MenuTypeEnum;
import com.cg.mljrpt.common.core.exception.ErrorCodes;
import com.cg.mljrpt.common.core.util.MsgUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	private final SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	@Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
	public Set<SysMenu> findMenuByRoleId(Long roleId) {
		return baseMapper.listMenusByRoleId(roleId);
	}

	/**
	 * ??????????????????
	 * @param id ??????ID
	 * @return true??????, false??????
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Boolean removeMenuById(Long id) {
		// ???????????????????????????????????????
		List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));

		Assert.isTrue(CollUtil.isEmpty(menuList), MsgUtils.getMessage(ErrorCodes.SYS_MENU_DELETE_EXISTING));

		sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
		// ?????????????????????????????????
		return this.removeById(id);
	}

	@Override
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Boolean updateMenuById(SysMenu sysMenu) {
		return this.updateById(sysMenu);
	}

	/**
	 * ??????????????? 1. ???????????????????????????????????? 2. ?????????????????????parentId ?????? 2.1 ???????????????????????????ID -1
	 * @param lazy ??????????????????
	 * @param parentId ?????????ID
	 * @return
	 */
	@Override
	public List<Tree<Long>> treeMenu(boolean lazy, Long parentId) {
		if (!lazy) {
			List<TreeNode<Long>> collect = baseMapper
					.selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSortOrder)).stream()
					.map(getNodeFunction()).collect(Collectors.toList());

			return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
		}

		Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;

		List<TreeNode<Long>> collect = baseMapper
				.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent)
						.orderByAsc(SysMenu::getSortOrder))
				.stream().map(getNodeFunction()).collect(Collectors.toList());

		return TreeUtil.build(collect, parent);
	}

	/**
	 * ????????????
	 * @param all ????????????
	 * @param parentId ?????????ID
	 * @return
	 */
	@Override
	public List<Tree<Long>> filterMenu(Set<SysMenu> all, Long parentId) {
		List<TreeNode<Long>> collect = all.stream()
				.filter(menu -> MenuTypeEnum.LEFT_MENU.getType().equals(menu.getType()))
				.filter(menu -> StrUtil.isNotBlank(menu.getPath())).map(getNodeFunction()).collect(Collectors.toList());
		Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
		return TreeUtil.build(collect, parent);
	}

	@Override
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public void clearMenuCache() {

	}

	@NotNull
	private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
		return menu -> {
			TreeNode<Long> node = new TreeNode<>();
			node.setId(menu.getMenuId());
			node.setName(menu.getName());
			node.setParentId(menu.getParentId());
			node.setWeight(menu.getSortOrder());
			// ????????????
			Map<String, Object> extra = new HashMap<>();
			extra.put("icon", menu.getIcon());
			extra.put("path", menu.getPath());
			extra.put("type", menu.getType());
			extra.put("permission", menu.getPermission());
			extra.put("label", menu.getName());
			extra.put("sortOrder", menu.getSortOrder());
			extra.put("keepAlive", menu.getKeepAlive());
			node.setExtra(extra);
			return node;
		};
	}

}
