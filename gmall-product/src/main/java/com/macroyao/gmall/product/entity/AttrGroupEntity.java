package com.macroyao.gmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.macroyao.common.valid.group.AddGroup;
import com.macroyao.common.valid.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * 属性分组
 * 
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分组id
	 */
	@NotNull(message = "{com.macroyao.gmall.product.id}",groups = {UpdateGroup.class})
	@Null(message = "{com.macroyao.gmall.product.notId}",groups = {AddGroup.class})
	@TableId
	private Long attrGroupId;
	/**
	 * 组名
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.name}",groups = {AddGroup.class})
	private String attrGroupName;
	/**
	 * 排序
	 */
	@NotNull(message = "{com.macroyao.gmall.product.sort.notnull}",groups = {AddGroup.class})
	@DecimalMin(value = "0",message = "{com.macroyao.gmall.product.sort.message}",groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;
	/**
	 * 描述
	 */
	private String descript;
	/**
	 * 组图标
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.icon}",groups = {AddGroup.class})
	private String icon;
	/**
	 * 所属分类id
	 */
	@NotNull(message = "{com.macroyao.gmall.product.catelogId.notnull}",groups = {AddGroup.class})
	@DecimalMin(value = "1",message ="{com.macroyao.gmall.product.catelogId.message}",groups = {AddGroup.class,UpdateGroup.class})
	private Long catelogId;

	@TableField(exist = false)
	private List<Long> path;

}
