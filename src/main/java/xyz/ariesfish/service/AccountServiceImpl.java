package xyz.ariesfish.service;

import xyz.ariesfish.dao.IAccountDao;
import xyz.ariesfish.domain.Account;
import xyz.ariesfish.utils.TransactionManager;

import java.util.List;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccounts() {
        List<Account> accounts = accountDao.findAllAccounts();
        return accounts;
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

    public void transfer(String srcName, String destName, Float money) {
        Account src = accountDao.findAccountByName(srcName);
        Account dest = accountDao.findAccountByName(destName);
        src.setMoney(src.getMoney() - money);
        dest.setMoney(dest.getMoney() + money);
        accountDao.updateAccount(src);
        //int i = 1/0;
        accountDao.updateAccount(dest);
    }
}
