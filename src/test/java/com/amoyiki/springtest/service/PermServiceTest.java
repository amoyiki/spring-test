package com.amoyiki.springtest.service;

import com.amoyiki.springtest.SpringTestApplicationTests;
import com.amoyiki.springtest.entry.Perm;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author amoyiki
 * @since 2019/3/8
 */
public class PermServiceTest extends SpringTestApplicationTests {

    @Autowired
    private PermService permService;

    @Test
    public void testFindPermListByRid(){
        List<Perm> list = permService.findPermListByRid(1L);
        Assert.assertTrue(list.size()>0);
    }
}