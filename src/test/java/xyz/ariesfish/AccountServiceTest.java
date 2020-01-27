package xyz.ariesfish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.ariesfish.domain.Account;
import xyz.ariesfish.service.IAccountService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    private IAccountService proxyAccountService;

    // use junit for testing
    @Test
    public void testFindAll() {
        List<Account> accounts = proxyAccountService.findAllAccounts();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        Account account = proxyAccountService.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testTransfer() {
        proxyAccountService.transfer("a", "b", 100f);
    }
}
