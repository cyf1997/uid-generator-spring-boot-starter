package com.github.cyf1997.uid;

import com.github.cyf1997.uid.impl.DefaultUidGenerator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class UidApplicationTests {

    @Resource
    private DefaultUidGenerator defaultUidGenerator;

    @Test
    void contextLoads() {
        System.out.println(defaultUidGenerator.getUID());
    }

}
