package com.admxj.spring.SpringDemo.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomStringUtilsTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testRandom() {

        String random = RandomStringUtils.randomNumeric(8);

        logger.info(random);


    }

}
