package com.macroyao.gmall.product.controller;

import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.R;
import com.macroyao.common.valid.group.AddGroup;
import com.macroyao.common.valid.group.UpdateGroup;
import com.macroyao.common.valid.group.UpdateStatusGroup;
import com.macroyao.gmall.product.entity.BrandEntity;
import com.macroyao.gmall.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 品牌
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    private ObjectError error;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@RequestBody /*@Valid*/ @Validated(value = {AddGroup.class}) BrandEntity brand/*BindingResult result*/){
//        Map<String, Object> map = new HashMap<>();
//        if(result.hasErrors()){
//            result.getFieldErrors().forEach(error -> {
//                String message = error.getDefaultMessage();
//                String field = error.getField();
//                map.put(field,message);
//            });
//            return R.error(400,"参数校检出错").put("data",map);
//        }else {
//            brandService.save(brand);
//            return R.ok();
//        }
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@RequestBody @Validated(value = {UpdateGroup.class}) BrandEntity brand){
		brandService.updateDetailById(brand);
        return R.ok();
    }

    @RequestMapping("/update/status")
    //@RequiresPermissions("product:brand:update")
    public R updateStatus(@RequestBody @Validated(value = {UpdateStatusGroup.class}) BrandEntity brand){
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
     	brandService.removeByIds(Arrays.asList(brandIds));
        return R.ok();
    }


}
