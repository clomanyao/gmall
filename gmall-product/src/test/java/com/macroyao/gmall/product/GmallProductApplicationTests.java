package com.macroyao.gmall.product;


import com.macroyao.gmall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GmallProductApplicationTests {

    @Autowired
    private CategoryService categoryService;


    @Test
    public void contextLoads() {
//        BrandEntity entity = new BrandEntity();
//        entity.setName("华为");
//        boolean b = brandService.save(entity);
//        System.out.println(b);
//        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
//        list.forEach(System.out::print);
//        List<Long> path = categoryService.getPath(225L);
//        log.info("完整路径:{}",path);
    }

}
