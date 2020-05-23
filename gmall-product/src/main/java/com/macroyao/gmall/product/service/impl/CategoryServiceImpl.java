package com.macroyao.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.Query;
import com.macroyao.gmall.product.dao.CategoryDao;
import com.macroyao.gmall.product.entity.CategoryEntity;
import com.macroyao.gmall.product.service.CategoryBrandRelationService;
import com.macroyao.gmall.product.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //查出所有的分类
        List<CategoryEntity> categoryEntities = this.list();
        List<CategoryEntity> list = categoryEntities.stream().filter(
                categoryEntity -> categoryEntity.getCatLevel() == 1
        ).map(categoryEntity -> {
            List<CategoryEntity> children = getChildren(categoryEntity, categoryEntities);
            categoryEntity.setChildren(children);
            categoryEntities.remove(children);
            return categoryEntity;
        }).sorted(
                (x1, x2) -> x1.getSort() == null ? 0 : x1.getSort() - (x2.getSort() == null ? 0 : x2.getSort())
        ).collect(Collectors.toList());
        return list;
    }

    @Override
    public boolean removeMenusByIds(List<Long> asList) {
        //TODO 首先要查找Category被那些给引用，引用的不能删除
        //删除采用逻辑删除，即删除应该是设置一个字段为0，0表示已经删除，1表示未删除
        return this.removeByIds(asList);
    }

    //递归查询孩子
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> list = all.stream().filter(
                categoryEntity -> categoryEntity.getParentCid() == root.getCatId()
        ).map(categoryEntity -> {
            List<CategoryEntity> children = getChildren(categoryEntity, all);
            categoryEntity.setChildren(children);
            all.remove(children);
            return categoryEntity;
        }).sorted(//catId=1431, name=dsa323, parentCid=1, catLevel=2, showStatus=1, sort=null, icon=null,
                (x1, x2) -> x1.getSort() == null ? 0 : x1.getSort() - (x2.getSort() == null ? 0 : x2.getSort())
        ).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Long> getPath(Long catelogId) {
        List<Long> path = getPath(catelogId,new ArrayList<>());
        Collections.reverse(path);
        return path;
    }

    @Transactional
    @Override
    public void updateDetailById(CategoryEntity category) {
        this.updateById(category);
        if(StringUtils.isNotBlank(category.getName())){
            categoryBrandRelationService.updateGategoryName(category.getCatId(),category.getName());
        }
    }

    private List<Long> getPath(Long catelogId, List<Long> path) {
        CategoryEntity categoryEntity = this.getById(catelogId);
        path.add(categoryEntity.getCatId());
        if (categoryEntity != null && categoryEntity.getParentCid() != 0) {
            getPath(categoryEntity.getParentCid(), path);
        }
        return path;
    }
}