import edu.kit.informatik.pcc.webinterface.datamanagement.Account;
import edu.kit.informatik.pcc.webinterface.serverconnection.ServerProxy;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author Christoph Hörtnagl
 * Created by chris on 03.03.2017.
 */
public class ServerProxyTest {
    private final String MAIL = "mail";
    private final String PASSWORD = "password";
    private final int VIDEOID = 1;
    private final String FAILURE = "FAILURE";
    private final String WRONGACCOUNT = "WRONG ACCOUNT";
    private final String NOTEXISTING = "NOT EXISTING";
    private Account account;
    private String token;
    private UUID uuid;

    @Before
    public void setUp() {
        account = new Account(MAIL, PASSWORD);
        token = "abc";
        uuid = UUID.randomUUID();
    }

    // Tests with a wrong Account

    @Test
    public void authenticateWrongAccountTest() {
        assertEquals(ServerProxy.authenticateUser(account), NOTEXISTING);
    }

    @Test
    public void createAccountFailTest() {
        assertEquals(ServerProxy.createAccount(account), FAILURE);
    }

    @Test
    public void changeAccountWrongAccountTest() {
        assertEquals(ServerProxy.changeAccount(account, account), WRONGACCOUNT);
    }

    @Test
    public void deleteAccountWrongAccountTest() {
        assertEquals(ServerProxy.deleteAccount(token), WRONGACCOUNT);
    }

}
