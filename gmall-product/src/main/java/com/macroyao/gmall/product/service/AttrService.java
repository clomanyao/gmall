package com.macroyao.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.gmall.product.entity.AttrEntity;
import com.macroyao.gmall.product.vo.AttrRespVo;
import com.macroyao.gmall.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:19
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    AttrRespVo getAttrRespVoById(Long attrId);

    void updateAttrVoById(AttrVo attr);

    PageUtils listNoAttrRelation(Map<String, Object> params, Long attrgroupId);
}

