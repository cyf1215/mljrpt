-- 该脚本不要直接执行， 注意维护菜单的父节点ID 默认 根节点

-- 菜单SQL
insert into `sys_menu` (`menu_id`, `parent_id`, `path`, `permission`, `type`, `icon`, `del_flag`, `create_time`, `sort_order`, `update_time`, `name`)
    values (1675907950010, '-1', '/demo/product/index', '', '0', 'icon-bangzhushouji', '0', '2018-01-20 13:17:19', '8', '2018-07-29 13:38:19', 'Insurance product management');

-- 菜单对应按钮SQL
insert into `sys_menu` ( `parent_id`,`menu_id`, `permission`, `type`, `path`, `icon`, `del_flag`, `create_time`, `sort_order`, `update_time`, `name`)
values(1675907950010, 1675907950011, 'demo_product_get', '1', null, '1',  '0', '2018-05-15 21:35:18', '0', '2018-07-29 13:38:59', 'View insurance products');

insert into `sys_menu` (`parent_id`, `menu_id`, `permission`, `type`, `path`, `icon`, `del_flag`, `create_time`, `sort_order`, `update_time`, `name`)
values(1675907950010, 1675907950012, 'demo_product_add', '1', null, '1',  '0', '2018-05-15 21:35:18', '1', '2018-07-29 13:38:59', 'Add Insurance Products');

insert into `sys_menu` (`parent_id`, `menu_id`, `permission`, `type`, `path`, `icon`,  `del_flag`, `create_time`, `sort_order`, `update_time`, `name`)
values(1675907950010, 1675907950013, 'demo_product_edit', '1', null, '1',  '0', '2018-05-15 21:35:18', '2', '2018-07-29 13:38:59', 'Update Insurance Products');

insert into `sys_menu` (`parent_id`, `menu_id`,  `permission`, `type`, `path`, `icon`, `del_flag`, `create_time`, `sort_order`, `update_time`, `name`)
values(1675907950010, 1675907950014, 'demo_product_del', '1', null, '1',  '0', '2018-05-15 21:35:18', '3', '2018-07-29 13:38:59', 'Delete Insurance Products');

