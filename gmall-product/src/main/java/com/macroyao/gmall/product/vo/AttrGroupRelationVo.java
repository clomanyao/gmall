package com.macroyao.gmall.product.vo;

import com.macroyao.common.valid.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AttrGroupRelationVo {

    @Min(value = 1,message = "{com.macroyao.gmall.product.attrId.message}",groups = {AddGroup.class})
    @NotNull(message = "{com.macroyao.gmall.product.attrId}",groups = {AddGroup.class})
    private Long attrId;
    @Min(value = 1,message = "{com.macroyao.gmall.product.attrGroupId.message}",groups = {AddGroup.class})
    @NotNull(message = "{com.macroyao.gmall.product.attrGroupId}",groups = {AddGroup.class})
    private Long attrGroupId;
}
