package com.macroyao.gmall.product.controller;

import com.macroyao.common.utils.PageUtils;
import com.macroyao.common.utils.R;
import com.macroyao.common.valid.group.AddGroup;
import com.macroyao.common.valid.group.UpdateGroup;
import com.macroyao.gmall.product.service.AttrService;
import com.macroyao.gmall.product.vo.AttrRespVo;
import com.macroyao.gmall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 商品属性
 *
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:19
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @GetMapping("/{attrType}/list/{catelogId}")
    //@RequiresPermissions("product:attr:list")
    //@RequestParam把请求参数封装到map中  GET方法,拼接参数(?x=x&y=y&...)使用@RequestParam把请求参数封装到map中
    public R listAttr(@RequestParam Map<String, Object> params,
                      @PathVariable("catelogId") Long catelogId,
                      @PathVariable("attrType") String attrType) {
        PageUtils page = attrService.queryBaseAttrPage(params,catelogId,attrType);
        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
//    @PostMapping("/base/list/{catelogId}")
//    //@RequiresPermissions("product:attr:list")
//    public R list(@RequestBody Map<String, Object> params) {
//        PageUtils page = attrService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    //product/attr/info/1
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrRespVo attr = attrService.getAttrRespVoById(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attr:save")
    public R save(@RequestBody @Validated(value = {AddGroup.class}) AttrVo attr) {
        attrService.saveDetail(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody @Validated(value = {UpdateGroup.class}) AttrVo attr) {
        attrService.updateAttrVoById(attr);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));
        return R.ok();
    }

}
