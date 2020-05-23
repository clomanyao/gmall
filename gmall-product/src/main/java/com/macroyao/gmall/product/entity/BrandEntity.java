package com.macroyao.gmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.macroyao.common.valid.group.AddGroup;
import com.macroyao.common.valid.Status;
import com.macroyao.common.valid.group.UpdateGroup;
import com.macroyao.common.valid.group.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 品牌
 * 
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 16:57:18
 */

/*
* 	@NotNull://CharSequence, Collection, Map 和 Array 对象不能是 null, 但可以是空集（size = 0）。
	@NotEmpty://CharSequence, Collection, Map 和 Array 对象不能是 null 并且相关对象的 size 大于 0。
	@NotBlank://String 不是 null 且去除两端空白字符后的长度（trimmed length）大于 0。
 JSR303 校检
 *   一  1 在每个字段上加入判断，例@NotNUll
 * 	 	2 @Vaild(controller中使用) BindingResult result 取结果
 *   二 全局的参数valid 异常处理器 处理异常 MethodArgumentNotValidException
 * 	 三 分组
 *     1 定义接口
 * 	   2 使用接口,例: @NotBlank(message = "品牌名不能为空!",groups = {AddGroup.class,UpdateGroup.class})
 * 	   3 @Validated(value = {xxx.class})
 *   四 自定义校验规则
 *    1 自定义校检注解
 *    2 自定义校检解析器
 *    3 绑定自定义注解和自定义校检解析器
 *
* */


@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@NotNull(message = "{com.macroyao.gmall.product.id}",groups = {UpdateGroup.class})
	@Null(message = "{com.macroyao.gmall.product.notId}",groups = {AddGroup.class})
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "{com.macroyao.gmall.product.name}",groups = {AddGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@URL(message = "{com.macroyao.gmall.product.logo.message}",groups = {AddGroup.class,UpdateGroup.class})
	@NotEmpty(message = "{com.macroyao.gmall.product.logo.notnull}",groups = {AddGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	//@TableLogic(value = "1",delval = "0")
	@NotNull(message = "{com.macroyao.gmall.product.showStatus}",groups = {AddGroup.class})
	//@Range(min = 0L,max = 1L,message = "状态只能为0或1",groups = {AddGroup.class,UpdateGroup.class})
	@Status(values={0,1},groups = {AddGroup.class, UpdateStatusGroup.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotBlank(message = "{com.macroyao.gmall.productfirstLetter.notnull}",groups = {AddGroup.class})
	@Pattern(regexp = "^[A-Za-z]$",message = "{com.macroyao.gmall.product.firstLetter.message}",groups = {AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "{com.macroyao.gmall.product.sort.notnull}",groups = {AddGroup.class})
	@DecimalMin(value = "0",message = "{com.macroyao.gmall.product.sort.message}",groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
