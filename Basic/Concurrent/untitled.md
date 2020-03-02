# 新增用户发货记录的时候，支持发货来源字段

[TOC]

## 任务

1. 新建一张表记录当前产品ID 及 库存，名为**blind_box_blind_box_source_storage**。
2. 当新增用户发货记录的时候，去查看产品的ID，以及对应的库存。
  2.0 当库存表 **blind_box_blind_box_source_storage** 记录不存在的时候:
  2.1 当库存不为0的时候，减少 **blind_box_blind_box_source_storage** 的库存，发货记录上发货来源字段记为 ？  
  2.2 当库存为0的时候: **blind_box_blind_box_source_storage** 不变，发货记录上发货来源字段记为 ？


## 内容

1. 

## 数据表

### 发货记录表

**blind_box_reward_record**，新加**source**字段:
```sql
CREATE TABLE `blind_box_reward_record` (
  `source` varchar(128) DEFAULT '0' COMMENT '订单商品的发货来源'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
```

### 发货来源的库存表

新建表 **blind_box_blind_box_source_storage**:
```sql
CREATE TABLE `blind_box_reward_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` varchar(100) DEFAULT NULL COMMENT '用户id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '用户id',

  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
```

## 问题

无
