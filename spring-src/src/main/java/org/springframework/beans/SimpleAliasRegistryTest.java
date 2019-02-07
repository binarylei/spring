package org.springframework.beans;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.SimpleAliasRegistry;

/**
 * @author: leigang
 * @version: 2019-01-19
 */
public class SimpleAliasRegistryTest {

    @Test
    public void test() {
        SimpleAliasRegistry aliasRegistry = new SimpleAliasRegistry();
        aliasRegistry.registerAlias("beanA", "beanA_alias1");
        aliasRegistry.registerAlias("beanA_alias1", "beanA_alias2");

        // 1. 获取别名对应的真实名称
        Assert.assertEquals("beanA", aliasRegistry.canonicalName("beanA_alias1"));
        Assert.assertEquals("beanA", aliasRegistry.canonicalName("beanA_alias2"));

        // 2. 获取 name 的所有别名
        Assert.assertEquals(2, aliasRegistry.getAliases("beanA").length);

        Assert.assertTrue(aliasRegistry.isAlias("beanA_alias1"));
        Assert.assertTrue(aliasRegistry.hasAlias("beanA", "beanA_alias2"));
    }


}
