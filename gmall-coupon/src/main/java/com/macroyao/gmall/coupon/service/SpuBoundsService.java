package com.macroyao.gmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.gmall.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 22:45:00
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

