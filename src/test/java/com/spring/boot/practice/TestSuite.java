package com.spring.boot.practice;

import com.spring.boot.practice.controller.ControllerTestSuite;
import com.spring.boot.practice.service.CustomerServiceTest;
import com.spring.boot.practice.service.ServiceTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by wangchong on 9/27/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ControllerTestSuite.class,
        ServiceTestSuite.class
})
public class TestSuite {
}
