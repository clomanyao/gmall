package com.macroyao.gmall.product;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.macroyao.gmall.product.entity.CategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GmallProductApplicationTests {

    @Autowired
    private BaseMapper<CategoryEntity> baseMapper;


    @Test
    public void contextLoads() {
//        BrandEntity entity = new BrandEntity();
//        entity.setName("华为");
//        boolean b = brandService.save(entity);
//        System.out.println(b);
//        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
//        list.forEach(System.out::print);
    }

}
