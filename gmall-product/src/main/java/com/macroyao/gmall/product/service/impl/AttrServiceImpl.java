package com.macroyao.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macroyao.common.constant.ProductConstant;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.Query;
import com.macroyao.gmall.product.dao.AttrDao;
import com.macroyao.gmall.product.entity.AttrAttrgroupRelationEntity;
import com.macroyao.gmall.product.entity.AttrEntity;
import com.macroyao.gmall.product.entity.AttrGroupEntity;
import com.macroyao.gmall.product.entity.CategoryEntity;
import com.macroyao.gmall.product.service.AttrAttrgroupRelationService;
import com.macroyao.gmall.product.service.AttrGroupService;
import com.macroyao.gmall.product.service.AttrService;
import com.macroyao.gmall.product.service.CategoryService;
import com.macroyao.gmall.product.vo.AttrRespVo;
import com.macroyao.gmall.product.vo.AttrVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveDetail(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.save(attrEntity);
        if ((attr.getAttrType() == ProductConstant.AttrTypeEnum.ATTR_TYPE_BASE.getCode())&&attr.getAttrGroupId()!=null) {
            AttrAttrgroupRelationEntity entity = new AttrAttrgroupRelationEntity();
            entity.setAttrGroupId(attr.getAttrGroupId());
            entity.setAttrId(attrEntity.getAttrId());
            entity.setAttrSort(0);
            attrAttrgroupRelationService.save(entity);
        }
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_type", "base".equalsIgnoreCase(attrType) ? ProductConstant.AttrTypeEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrTypeEnum.ATTR_TYPE_SALE.getCode());
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.eq("attr_id", key).or().like("attr_name", key);
        }
        if (catelogId != 0) {
            wrapper.and(attrEntityQueryWrapper -> {
                attrEntityQueryWrapper.eq("catelog_id", catelogId);  //注and
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);
        List<AttrRespVo> list = new ArrayList<>();
        page.getRecords().forEach(attrEntity -> {
            AttrRespVo respVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, respVo);
            if ("base".equalsIgnoreCase(attrType)) {
                AttrAttrgroupRelationEntity entity = attrAttrgroupRelationService.getOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if (entity != null) {
                    AttrGroupEntity attrGroupEntity = attrGroupService.getById(entity.getAttrGroupId());
                    if (attrGroupEntity != null) {  //防止分组被删除后，产生空指针异常
                        respVo.setGroupName(attrGroupEntity.getAttrGroupName());
                    }
                }
            }
            CategoryEntity categoryEntity = categoryService.getById(attrEntity.getCatelogId());
            if (categoryEntity != null) {
                respVo.setCatelogName(categoryEntity.getName());
            }
            list.add(respVo);
        });
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(list);
        return pageUtils;
    }


    @Override
    public AttrRespVo getAttrRespVoById(Long attrId) {
        AttrRespVo respVo = new AttrRespVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, respVo);
        if (attrEntity.getAttrType() == ProductConstant.AttrTypeEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity relationEntity = attrAttrgroupRelationService.getOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            if (relationEntity != null) {
                respVo.setAttrGroupId(relationEntity.getAttrGroupId());
                AttrGroupEntity groupEntity = attrGroupService.getById(relationEntity.getAttrGroupId());
                if (groupEntity != null) {
                    respVo.setGroupName(groupEntity.getAttrGroupName());
                }
            }
        }
        CategoryEntity categoryEntity = categoryService.getById(attrEntity.getCatelogId());
        if (categoryEntity != null) {
            respVo.setCatelogName(categoryEntity.getName());
        }
        respVo.setCatelogPath(categoryService.getPath(attrEntity.getCatelogId()));
        return respVo;
    }

    @Transactional
    @Override
    public void updateAttrVoById(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);
        this.updateById(attrEntity); // 3310
        if (attrEntity.getAttrType() == ProductConstant.AttrTypeEnum.ATTR_TYPE_BASE.getCode()) {
            int count = attrAttrgroupRelationService.count(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attrVo.getAttrGroupId());
            relationEntity.setAttrId(attrVo.getAttrId());
            if (count > 0) {//更新
                attrAttrgroupRelationService.update(relationEntity, new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", relationEntity.getAttrId()));
            } else {//添加
                relationEntity.setAttrSort(0);
                attrAttrgroupRelationService.save(relationEntity);
            }
        }
    }

    @Override
    public PageUtils listNoAttrRelation(Map<String, Object> params, Long attrgroupId) {
        //查询出当前组所在的分类
        AttrGroupEntity groupEntity = attrGroupService.getById(attrgroupId);

        //拿到其他组的groupIds
        List<AttrGroupEntity> groupEntities = attrGroupService.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", groupEntity.getCatelogId()));
        List<Long> groupIds = groupEntities.stream().map(entity -> entity.getAttrGroupId()).collect(Collectors.toList());
        //查询这些分组已经被绑定的属性 attrIds
        List<AttrAttrgroupRelationEntity> relationEntities = attrAttrgroupRelationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", groupIds));
        List<Long> attrIds = relationEntities.stream().map(entity -> entity.getAttrId()).collect(Collectors.toList());

        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {  //模糊查询
            wrapper.eq("attr_id", key).or().like("attr_name", key);
        }
        //查询当前分类下的所有 基本属性 ,并且筛选出那些一个分类中没有被组绑定的基本属性的AttrEntity
        wrapper.eq("catelog_id", groupEntity.getCatelogId())
                .eq("attr_type", ProductConstant.AttrTypeEnum.ATTR_TYPE_BASE.getCode())
                .notIn("attr_id", attrIds);
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }

}