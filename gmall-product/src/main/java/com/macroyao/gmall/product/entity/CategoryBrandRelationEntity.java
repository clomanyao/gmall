package com.macroyao.gmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.macroyao.common.valid.group.AddCategoryBrandGroup;
import com.macroyao.common.valid.group.AddGroup;
import com.macroyao.common.valid.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 品牌分类关联
 * 
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@NotNull(message = "{com.macroyao.gmall.product.id}",groups = {UpdateGroup.class})
	@Null(message = "{com.macroyao.gmall.product.notId}",groups = {AddGroup.class})
	@TableId
	private Long id;
	/**
	 * 品牌id
	 */
	@NotNull(message = "{com.macroyao.gmall.product.id}",groups = {AddGroup.class, AddCategoryBrandGroup.class})
	private Long brandId;
	/**
	 * 分类id
	 */
	@NotNull(message = "{com.macroyao.gmall.product.id}",groups = {AddGroup.class,AddCategoryBrandGroup.class})
	private Long catelogId;
	/**
	 * 
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.name}",groups = {AddGroup.class})
	private String brandName;
	/**
	 * 
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.name}",groups = {AddGroup.class})
	private String catelogName;

}
