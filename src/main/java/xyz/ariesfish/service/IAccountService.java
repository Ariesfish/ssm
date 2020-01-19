package xyz.ariesfish.service;

import xyz.ariesfish.domain.Account;

import java.util.List;

public interface IAccountService {

    List<Account> findAllAccounts();

    Account findAccountById(Integer accountId);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);
}
