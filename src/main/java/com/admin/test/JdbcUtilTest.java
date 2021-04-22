package com.admin.test;

import com.admin.utils.JdbcUtil;
import org.junit.Test;

/**
 * @author tanquan
 * @description <p>
 * </p>
 * @date 5/26/2019 11:06 AM
 */
public class JdbcUtilTest {

    @Test
    public void getConn(){
        System.out.println(JdbcUtil.getConn());
    }

}
