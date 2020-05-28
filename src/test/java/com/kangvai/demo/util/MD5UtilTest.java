package com.kangvai.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kangvai
 * @date 2020/5/27 15:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MD5UtilTest {

    /**
     * 密码加密算法测试
     * @params []
     * @return void
     */
    @Test
    public void MD5UtilTest() {
        String password = "user@163";
        String passwordMD5 = MD5Util.encrypt(password);
        log.info("passwordMD5"+passwordMD5);
    }
}
