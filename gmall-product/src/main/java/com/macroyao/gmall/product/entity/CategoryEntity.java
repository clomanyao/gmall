package com.macroyao.gmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.macroyao.common.valid.group.AddGroup;
import com.macroyao.common.valid.Status;
import com.macroyao.common.valid.group.UpdateGroup;
import com.macroyao.common.valid.group.UpdateStatusGroup;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * 商品三级分类
 * 
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
@Data
@TableName("pms_category")
@ToString
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@NotNull(message = "{com.macroyao.gmall.product.id}",groups = {UpdateGroup.class})
	@Null(message = "{com.macroyao.gmall.product.notId}",groups = {AddGroup.class})
	@TableId
	private Long catId;
	/**
	 * 分类名称
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.name}",groups = {AddGroup.class})
	private String name;
	/**
	 * 父分类id
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.parentCid.notnull}",groups = {AddGroup.class})
	@DecimalMin(value = "0",message = "{com.macroyao.gmall.product.parentCid.message}",groups = {AddGroup.class,UpdateGroup.class})
	private Long parentCid;
	/**
	 * 层级
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.catLevel.notnull}",groups = {AddGroup.class})
	@Range(min=1L,max = 3L,message = "{com.macroyao.gmall.product.catLevel.message}",groups = {AddGroup.class,UpdateGroup.class})
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	@NotNull(message = "{com.macroyao.gmall.product.showStatus}",groups = {AddGroup.class})
	//@Range(min = 0L,max = 1L,message = "状态只能为0或1",groups = {AddGroup.class,UpdateGroup.class})
	@Status(values={0,1},groups = {AddGroup.class, UpdateStatusGroup.class})
	@TableLogic(value = "1",delval = "0")
	private Integer showStatus;
	/**
	 * 排序
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.sort.notnull}",groups = {AddGroup.class})
	@DecimalMin(value = "0",message = "{com.macroyao.gmall.product.sort.message}",groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;
	/**
	 * 图标地址
	 */
	@NotNull(message = "{com.macroyao.gmall.product.icon}",groups = {AddGroup.class})
	private String icon;
	/**
	 * 计量单位
	 */

	private String productUnit;
	/**
	 * 商品数量
	 */
	private Integer productCount;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY )
	@TableField(exist = false) //表示数据库中没有这个字段
	private List<CategoryEntity> children;

}
