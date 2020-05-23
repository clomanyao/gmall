package com.macroyao.gmall.product.controller;

import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.R;
import com.macroyao.common.valid.group.AddCategoryBrandGroup;
import com.macroyao.gmall.product.entity.BrandEntity;
import com.macroyao.gmall.product.entity.CategoryBrandRelationEntity;
import com.macroyao.gmall.product.service.CategoryBrandRelationService;
import com.macroyao.gmall.product.vo.BrandRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 品牌分类关联
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    //product/categorybrandrelation/brands/list
    @GetMapping("/brands/list")
    public R listBrandsBycatId(@RequestParam(value = "catId",required = true) Long catId){
       List<BrandEntity> brandEntities=categoryBrandRelationService.listBrandsBycatId(catId);
        List<BrandRespVo> brandRespVos = brandEntities.stream().map(brandEntity -> {
            BrandRespVo vo = new BrandRespVo();
            vo.setBrandId(brandEntity.getBrandId());
            vo.setBrandName(brandEntity.getName());
            return vo;
        }).collect(Collectors.toList());
        return R.ok().put("data",brandRespVos);

    }

    @GetMapping("/catelog/list")
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R cateloglist(@RequestParam(value = "brandId") Long brandId) {
        List<CategoryBrandRelationEntity> data = categoryBrandRelationService.queryById(brandId);
        return R.ok().put("data", data);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id) {
        CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody @Validated(value = {AddCategoryBrandGroup.class}) CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.saveDetail(categoryBrandRelation);
        return R.ok();
    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
//    //@RequiresPermissions("product:categorybrandrelation:update")
//    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
//        categoryBrandRelationService.updateById(categoryBrandRelation);
//
//        return R.ok();
//    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids) {
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
