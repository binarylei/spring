package com.github.binarylei.cache;

import com.github.binarylei.cache.bean.Account;
import com.github.binarylei.cache.service.AccountService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CacheApplicationTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CacheConfigure.class);

        AccountService accountService = context.getBean(AccountService.class);
        Account account1 = accountService.getAccountByName("leigang");
        System.out.println(account1);

        Account account2 = accountService.getAccountByName("leigang");
        System.out.println(account2);
    }
}
