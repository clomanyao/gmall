package com.macroyao.gmall.product.vo;

import lombok.Data;

import java.util.List;

@Data
public class AttrRespVo extends AttrVo{

    private String groupName;
    private String catelogName;
    private List<Long> catelogPath;
}
