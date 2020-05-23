package com.macroyao.gmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.gmall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 22:45:01
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

