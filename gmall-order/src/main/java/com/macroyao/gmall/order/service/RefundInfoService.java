package com.macroyao.gmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.gmall.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 23:00:35
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

