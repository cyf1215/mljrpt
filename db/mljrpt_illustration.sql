-- 创建测试库
create database `mljrpt_illustration` default character set utf8mb4 collate utf8mb4_general_ci;

USE mljrpt_illustration;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建illustration的保险产品表
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint NOT NULL COMMENT 'PRIMARY KEY',
  `abbreviation` varchar(255) DEFAULT NULL COMMENT 'Abbreviation',
  `official_name` varchar(255) DEFAULT NULL COMMENT 'Official name',
  `nickname` varchar(255) DEFAULT NULL COMMENT 'Nickname',
  `period` varchar(255) DEFAULT NULL COMMENT 'Period',
  `primary_insurance` varchar(255) DEFAULT NULL COMMENT 'Principal insurance',
  `additional_insurance1` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 1',
  `additional_insurance2` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 2',
  `additional_insurance3` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 3',
  `additional_insurance4` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 4',
  `additional_insurance5` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 5',
  `additional_insurance6` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 6',
  `additional_insurance7` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 7',
  `additional_insurance8` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 8',
  `additional_insurance9` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 9',
  `additional_insurance10` varchar(255) DEFAULT NULL COMMENT 'Additional insurance 10',
  `additional_premium1` INT DEFAULT NULL COMMENT 'Additional premium 1',
  `additional_premium2` INT DEFAULT NULL COMMENT 'Additional premium 2',
  `additional_premium3` INT DEFAULT NULL COMMENT 'Additional premium 3',
  `additional_premium4` INT DEFAULT NULL COMMENT 'Additional premium 4',
  `additional_premium5` INT DEFAULT NULL COMMENT 'Additional premium 5',
  `additional_premium6` INT DEFAULT NULL COMMENT 'Additional premium 6',
  `additional_premium7` INT DEFAULT NULL COMMENT 'Additional premium 7',
  `additional_premium8` INT DEFAULT NULL COMMENT 'Additional premium 8',
  `additional_premium9` INT DEFAULT NULL COMMENT 'Additional premium 9',
  `additional_premium10` INT DEFAULT NULL COMMENT 'Additional premium 10',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='Insurance Products';