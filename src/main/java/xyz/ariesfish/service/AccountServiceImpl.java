package xyz.ariesfish.service;

import xyz.ariesfish.dao.IAccountDao;
import xyz.ariesfish.domain.Account;

import java.util.List;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    // for IoC
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccounts() {
        return accountDao.findAllAccounts();
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }
}
