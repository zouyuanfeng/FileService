package com.itzyf.meizhu

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired


@RunWith(SpringRunner::class)
@SpringBootTest
class FileServiceApplicationTests {
    @Autowired
    private val myProps: GirlProperties? = null

    @Test
    fun contextLoads() {
        print(myProps.toString())
    }

}
