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

    private TransactionManager txManager;
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public List<Account> findAllAccounts() {
        try {
            // begin
            txManager.beginTransaction();
            // do
            List<Account> accounts = accountDao.findAllAccounts();
            // commit
            txManager.commit();
            // return
            return accounts;
        } catch (Exception e) {
            // rollback
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
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

        try {
            txManager.beginTransaction();

            Account src = accountDao.findAccountByName(srcName);
            Account dest = accountDao.findAccountByName(destName);
            src.setMoney(src.getMoney() - money);
            dest.setMoney(dest.getMoney() + money);
            accountDao.updateAccount(src);
            accountDao.updateAccount(dest);

            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
}
