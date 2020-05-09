package com.macroyao.gmall.product.dao;

import com.macroyao.gmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
