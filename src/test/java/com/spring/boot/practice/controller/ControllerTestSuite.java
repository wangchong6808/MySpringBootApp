package com.spring.boot.practice.controller;

import com.spring.boot.practice.TestProfileValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;

/**
 * Created by wangchong on 9/27/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerRestControllerTest.class,
        UserRestControllerTest.class,
        MultipartRestControllerTest.class
})
public class ControllerTestSuite {
}
