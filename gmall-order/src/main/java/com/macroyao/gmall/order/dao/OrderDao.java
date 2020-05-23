package com.macroyao.gmall.order.dao;

import com.macroyao.gmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 23:00:35
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
