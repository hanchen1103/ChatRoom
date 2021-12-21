package com.distributed.roomprovider;

import com.distributed.roomprovider.util.JedisAdapterForSession;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class RoomProviderApplicationTests {

    @Autowired
    JedisAdapterForSession jedisAdapterForSession;

    @Test
    void contextLoads() {
        jedisAdapterForSession.setex("hello", "92-b03c-02a728704c68", 3600L);
    }

}
