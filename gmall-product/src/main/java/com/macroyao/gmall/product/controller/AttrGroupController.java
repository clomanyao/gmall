package com.macroyao.gmall.product.controller;

import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.R;
import com.macroyao.common.valid.group.AddGroup;
import com.macroyao.common.valid.group.UpdateGroup;
import com.macroyao.gmall.product.entity.AttrEntity;
import com.macroyao.gmall.product.entity.AttrGroupEntity;
import com.macroyao.gmall.product.service.AttrAttrgroupRelationService;
import com.macroyao.gmall.product.service.AttrGroupService;
import com.macroyao.gmall.product.service.AttrService;
import com.macroyao.gmall.product.service.CategoryService;
import com.macroyao.gmall.product.vo.AttrGroupRelationVo;
import com.macroyao.gmall.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 属性分组
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    //product/attrgroup/{catelogId}/withattr
    @GetMapping("{catelogId}/withattr")
    public R listAttrGroupWithAttrsByCatelogId(@PathVariable("catelogId") Long catelogId){
        List<AttrGroupWithAttrsVo> vos=attrGroupService.listAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",vos);
    }
    //product/attrgroup/attr/relation
    @PostMapping("/attr/relation")
    public R saveAttrRelation(@RequestBody @Validated(value = {AddGroup.class}) List<AttrGroupRelationVo> vos){
        attrAttrgroupRelationService.saveAttrRelation(vos);
        return R.ok();
    }
    //product/attrgroup/1/noattr/relation?
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R noattrRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params){
        PageUtils page=attrService.listNoAttrRelation(params,attrgroupId);
        return R.ok().put("page",page);
    }

    //product/attrgroup/attr/relation/delete
    @PostMapping("/attr/relation/delete")
    public R delAttrRelation(@RequestBody AttrGroupRelationVo[] vos){
        attrGroupService.deleteBatch(vos);
        return R.ok();
    }
    //product/attrgroup/1/attr/relation
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> list=attrGroupService.listAttr(attrgroupId);
        return R.ok().put("data",list);
    }
    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") Long catelogId){
        //PageUtils page = attrGroupService.queryPage(params);
        PageUtils page=attrGroupService.queryPage(params,catelogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        attrGroup.setPath(categoryService.getPath(attrGroup.getCatelogId()));
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody @Validated(value = {AddGroup.class}) AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody @Validated(value = {UpdateGroup.class}) AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return R.ok();
    }

}
