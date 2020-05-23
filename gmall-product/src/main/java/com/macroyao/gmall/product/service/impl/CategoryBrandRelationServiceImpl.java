package com.macroyao.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.Query;
import com.macroyao.gmall.product.dao.CategoryBrandRelationDao;
import com.macroyao.gmall.product.entity.BrandEntity;
import com.macroyao.gmall.product.entity.CategoryBrandRelationEntity;
import com.macroyao.gmall.product.entity.CategoryEntity;
import com.macroyao.gmall.product.service.BrandService;
import com.macroyao.gmall.product.service.CategoryBrandRelationService;
import com.macroyao.gmall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryBrandRelationEntity> queryById(Long brandId) {
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_id", brandId);
        return this.list(wrapper);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        CategoryEntity categoryEntity = categoryService.getById(categoryBrandRelation.getCatelogId());
        BrandEntity brandEntity = brandService.getById(categoryBrandRelation.getBrandId());
        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());
        this.save(categoryBrandRelation);
    }

    @Override
    public void updateBrandName(Long brandId, String name) {
        CategoryBrandRelationEntity entity = new CategoryBrandRelationEntity();
        entity.setBrandId(brandId);
        entity.setBrandName(name);
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        this.update(entity,wrapper.eq("brand_id",entity.getBrandId()));
    }

    @Override
    public void updateGategoryName(Long catId, String name) {
        this.getBaseMapper().updateGategoryName(catId,name);
    }

    @Override
    public List<BrandEntity> listBrandsBycatId(Long catId) {
        List<CategoryBrandRelationEntity> relationEntities = this.list(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        List<BrandEntity> brandEntities = relationEntities.stream().map(relationEntity -> {
            BrandEntity brandEntity = brandService.getById(relationEntity.getBrandId());
            return brandEntity;
        }).collect(Collectors.toList());
        return brandEntities;
    }

}