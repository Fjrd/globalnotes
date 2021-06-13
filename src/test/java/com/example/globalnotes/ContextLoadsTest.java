package com.example.globalnotes;


import com.example.globalnotes.configs.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class ContextLoadsTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads(){
        assertNotNull(context.getBean("entityManager"));
    }
}
