package com.macroyao.gmall.member.fegin;

import com.macroyao.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("gmall-coupon") //告诉Springcloud去哪个服务调用,服务名
public interface CouponFeginService {

    /*
    1 url 必须是完全路径
    2 方法有参数，加上参数
    * */
    @GetMapping("/coupon/coupon/member/list")
    public R list();
}
