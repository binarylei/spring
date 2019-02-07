package com.github.binarylei.cache.service;

import com.github.binarylei.cache.bean.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    // 使用了一个缓存名叫 accountCache
    @Cacheable(value = "accountCache")
    public Account getAccountByName(String accountName) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        return accountOptional.get();
    }

    @Cacheable(value = "accountCache", key = "#accountName.concat(#password)")
    public Account getAccount(String accountName, String password, boolean sendLog) {
        // 方法内部实现不考虑缓存逻辑。直接实现业务
        return getFromDB(accountName, password).get();
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying account from db... {}", accountName);
        //Todo query data from database
        return Optional.ofNullable(new Account(accountName));
    }

    private Optional<Account> getFromDB(String accountName, String password) {
        logger.info("real querying account from db... {}", accountName);
        //Todo query data from database
        return Optional.ofNullable(new Account(accountName));
    }

    @CacheEvict(value = "accountCache", key = "#account.getName()")
    public void updateAccount(Account account) {
        updateDB(account);
    }

    @CacheEvict(value = "accountCache", allEntries = true)
    public void reload() {
    }

    private void updateDB(Account account) {
        logger.info("real update db...{}", account.getName());
    }

}
