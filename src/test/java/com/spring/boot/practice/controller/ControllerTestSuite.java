package com.spring.boot.practice.controller;

import com.spring.boot.practice.controller.CustomerRestControllerTest;
import com.spring.boot.practice.controller.MultipartRestControllerTest;
import com.spring.boot.practice.controller.UserRestControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
