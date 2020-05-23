package com.macroyao.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.gmall.product.entity.AttrEntity;
import com.macroyao.gmall.product.entity.AttrGroupEntity;
import com.macroyao.gmall.product.vo.AttrGroupRelationVo;
import com.macroyao.gmall.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrEntity> listAttr(Long attrgroupId);

    void deleteBatch(AttrGroupRelationVo... vos);

    List<AttrGroupWithAttrsVo> listAttrGroupWithAttrsByCatelogId(Long catelogId);
}

