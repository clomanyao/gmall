package com.macroyao.gmall.member.dao;

import com.macroyao.gmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 22:54:55
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
