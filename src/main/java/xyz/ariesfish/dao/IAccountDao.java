package xyz.ariesfish.dao;

import xyz.ariesfish.domain.Account;

import java.util.List;

public interface IAccountDao {

    List<Account> findAllAccounts();

    Account findAccountById(Integer accountId);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);
}
