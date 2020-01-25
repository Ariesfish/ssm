package xyz.ariesfish;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.ariesfish.domain.Account;
import xyz.ariesfish.service.IAccountService;

import java.util.List;

public class AccountServiceTest {

    // use junit for testing
    @Test
    public void testFindAll() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        List<Account> accounts = as.findAllAccounts();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {

    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testDelete() {

    }
}
