package com.macroyao.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.Query;
import com.macroyao.gmall.product.dao.BrandDao;
import com.macroyao.gmall.product.entity.BrandEntity;
import com.macroyao.gmall.product.service.BrandService;
import com.macroyao.gmall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        String key =(String) params.get("key");
        if(StringUtils.isNotBlank(key)){
            wrapper.like("name",key).or().eq("first_letter",key).or().eq("brand_id",key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void updateDetailById(BrandEntity brand) {
        this.updateById(brand);
        if(StringUtils.isNotBlank(brand.getName())){
            categoryBrandRelationService.updateBrandName(brand.getBrandId(),brand.getName());
        }
    }


}