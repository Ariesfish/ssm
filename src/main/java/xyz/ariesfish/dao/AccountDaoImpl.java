package xyz.ariesfish.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import xyz.ariesfish.domain.Account;

import java.util.List;

public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {

    public List<Account> findAllAccounts() {
        try {
            return getJdbcTemplate().query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            List<Account> accounts = getJdbcTemplate().query("select * from account where id = ?",
                    new BeanPropertyRowMapper<Account>(Account.class), accountId);
            return accounts.isEmpty() ? null : accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = getJdbcTemplate().query("select * from account where name = ?",
                    new BeanPropertyRowMapper<Account>(Account.class), accountName);
            if (accounts.isEmpty()) {
                return null;
            }
            if (accounts.size() > 1) {
                throw new RuntimeException("Not only one record.");
            }
            return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try {
            getJdbcTemplate().update("insert into account(name, money)values(?,?)",
                    account.getName(), account.getMoney());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try {
            getJdbcTemplate().update("update account set name=?, money=? where id=?",
                    account.getName(), account.getMoney(), account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            getJdbcTemplate().update("delete from account where id=?", accountId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
