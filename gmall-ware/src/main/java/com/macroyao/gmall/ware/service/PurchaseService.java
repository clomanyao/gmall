package com.macroyao.gmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.gmall.ware.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 23:11:09
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

