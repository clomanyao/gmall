package com.macroyao.gmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.gmall.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 23:00:35
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

