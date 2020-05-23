package com.macroyao.gmall.product.vo;

import com.macroyao.common.valid.*;
import com.macroyao.common.valid.group.AddGroup;
import com.macroyao.common.valid.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class AttrVo {

    /**
     * 属性id
     */
    @NotNull(message = "{com.macroyao.gmall.product.id}",groups = {UpdateGroup.class})
    @Null(message = "{com.macroyao.gmall.product.notId}",groups = {AddGroup.class})
    private Long attrId;
    /**
     * 属性名
     */
    @NotBlank(message = "{com.macroyao.gmall.product.name}",groups = {AddGroup.class})
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @NotNull(message = "{com.macroyao.gmall.product.searchType}",groups = {AddGroup.class})
    @Status(values={0,1},groups = {AddGroup.class, UpdateGroup.class})
    private Integer searchType;
    /**
     * 属性图标
     */
    @NotNull(message = "{com.macroyao.gmall.product.icon}",groups = {AddGroup.class})
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    @NotNull(message = "{com.macroyao.gmall.product.valueSelect}",groups = {AddGroup.class})
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @NotNull(message = "{com.macroyao.gmall.product.attrType}",groups = {AddGroup.class})
    @Status(values={0,1,2},groups = {AddGroup.class, UpdateGroup.class})
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @NotNull(message = "{com.macroyao.gmall.product.enable}",groups = {AddGroup.class})
    @Range(min = 0L,max=1L,message = "{com.macroyao.common.valid.Status.message}",groups ={AddGroup.class, UpdateGroup.class} )
    private Long enable;
    /**
     * 所属分类
     */

    private Long catelogId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @NotNull(message = "{com.macroyao.gmall.product.showDesc}",groups = {AddGroup.class})
    @Status(values={0,1},groups = {AddGroup.class, UpdateGroup.class})
    private Integer showDesc;

    /*
    * 所属分组的id
    * */
    private Long attrGroupId;
}
