package com.macroyao.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.Query;
import com.macroyao.gmall.product.dao.AttrAttrgroupRelationDao;
import com.macroyao.gmall.product.entity.AttrAttrgroupRelationEntity;
import com.macroyao.gmall.product.service.AttrAttrgroupRelationService;
import com.macroyao.gmall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(List<AttrAttrgroupRelationEntity> entities) {
        this.getBaseMapper().deleteBatch(entities);
    }

    @Override
    public void saveAttrRelation(List<AttrGroupRelationVo> vos) {
        List<AttrAttrgroupRelationEntity> relationEntities = vos.stream().map(vo -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(vo, relationEntity);
            relationEntity.setAttrSort(0);
            return relationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(relationEntities);
    }

}