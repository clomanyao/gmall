package com.macroyao.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.Query;
import com.macroyao.gmall.product.dao.AttrGroupDao;
import com.macroyao.gmall.product.entity.AttrAttrgroupRelationEntity;
import com.macroyao.gmall.product.entity.AttrEntity;
import com.macroyao.gmall.product.entity.AttrGroupEntity;
import com.macroyao.gmall.product.service.AttrAttrgroupRelationService;
import com.macroyao.gmall.product.service.AttrGroupService;
import com.macroyao.gmall.product.service.AttrService;
import com.macroyao.gmall.product.vo.AttrGroupRelationVo;
import com.macroyao.gmall.product.vo.AttrGroupWithAttrsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        //select * from pms_attr_group where attr_group_id=key or attr_group_name=%key%
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        if(catelogId==0){
            if(StringUtils.isNotBlank(key)){
                wrapper.eq("attr_group_id",key).or().like("attr_group_name",key);
            }
        }else {
            // select * from pms_attr_group where catelog_id=#{catelog_id} and (attr_group_id=key or attr_group_name=%key%)
            wrapper.eq("catelog_id",catelogId);
            if(StringUtils.isNotBlank(key)) {
                wrapper.and(querywrapper->{
                    querywrapper.eq("attr_group_id",key).or().like("attr_group_name",key);
                });
            }
        }
        IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }

    @Override
    public List<AttrEntity> listAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> relationEntities = attrAttrgroupRelationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        List<Long> attrIds = relationEntities.stream().map(relationEntity -> relationEntity.getAttrId()).collect(Collectors.toList());
        if(attrIds!=null&&attrIds.size()>0){
            return attrService.listByIds(attrIds);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteBatch(AttrGroupRelationVo... vos) {
        List<AttrAttrgroupRelationEntity> entities = Arrays.asList(vos).stream().map(vo -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(vo, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        attrAttrgroupRelationService.deleteBatch(entities);
    }

    @Override
    public List<AttrGroupWithAttrsVo> listAttrGroupWithAttrsByCatelogId(Long catelogId) {
        if(catelogId!=null&&catelogId>0){
            List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
            List<AttrGroupWithAttrsVo> vos = attrGroupEntities.stream().map(attrGroupEntity -> {
                AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
                BeanUtils.copyProperties(attrGroupEntity, attrGroupWithAttrsVo);
                QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupEntity.getAttrGroupId());
                List<Long> attrIds = attrAttrgroupRelationService.list(wrapper).stream().map(relationEntity -> relationEntity.getAttrId()).collect(Collectors.toList());
                attrGroupWithAttrsVo.setAttrs(attrService.listByIds(attrIds));
                return attrGroupWithAttrsVo;
            }).collect(Collectors.toList());
            return vos;
        }
        return Collections.EMPTY_LIST;
    }


}