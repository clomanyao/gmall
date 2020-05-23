package com.macroyao.gmall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;


@SpringBootTest
@RunWith(SpringRunner.class)
public class GmallThirdPartyApplicationTests {

    @Autowired
    private OSSClient ossClient;

    @Test
    public void contextLoads() {
        ossClient.putObject("gmall-macroyao","images/123.jpg",new File("C:\\Users\\lenovo\\Pictures\\Camera Roll\\3.jpg"));
        ossClient.shutdown();
        System.out.println("发送成功");
    }

}
