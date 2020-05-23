package com.macroyao.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.gmall.product.entity.BrandEntity;
import com.macroyao.gmall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryBrandRelationEntity> queryById(Long brandId);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updateBrandName(Long brandId, String name);

    void updateGategoryName(Long catId, String name);

    List<BrandEntity> listBrandsBycatId(Long catId);
}

