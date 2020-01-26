package xyz.ariesfish;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.ariesfish.dao.IAccountDao;
import xyz.ariesfish.domain.Account;

import java.util.List;

public class AccountServiceTest {

    // use junit for testing
    @Test
    public void testFindAll() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao dao = ac.getBean("accountDao", IAccountDao.class);
        List<Account> accounts = dao.findAllAccounts();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao dao = ac.getBean("accountDao", IAccountDao.class);
        Account account = dao.findAccountById(1);
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
