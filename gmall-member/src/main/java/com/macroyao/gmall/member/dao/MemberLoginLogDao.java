package com.macroyao.gmall.member.dao;

import com.macroyao.gmall.member.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author macroyao
 * @email 2482118722@qq.com
 * @date 2020-05-09 22:54:54
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
