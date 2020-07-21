import de.steinwedel.messagebox.MessageBox;
import edu.kit.informatik.pcc.webinterface.datamanagement.AccountDataManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Tests the AccountManager functionality
 *
 * @author Christoph Hörtnagl
 * Created by chris on 03.03.2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MessageBox.class})
public class AccountDataManagerTest {
    private final String VALIDMAIL = "mail@mail.mail";
    private final String NOTVALIDMAIL = "mail";
    private final String PASSWORD = "password";
    private final String SHORTPASSWORD = "123";
    private MessageBoxStub messageBoxStub;
    private SessionStub sessionStub;

    @Before
    public void setUp() {
        messageBoxStub = new MessageBoxStub();
        sessionStub = new SessionStub(null);
        PowerMockito.mockStatic(MessageBox.class);
        when(MessageBox.createInfo()).thenReturn(messageBoxStub);
    }

    @Test
    public void createAccountTest() {
        // tries with invalid mail
        assertEquals(AccountDataManager.createAccount(NOTVALIDMAIL, PASSWORD, sessionStub), false);
        // tries with invalid password
        assertEquals(AccountDataManager.createAccount(VALIDMAIL, SHORTPASSWORD, sessionStub), false);
    }

    @Test
    public void changeAccountTest() {
        // when empty
        assertEquals(AccountDataManager.changeAccount(PASSWORD, "", "", sessionStub), false);
        // when no valid mail
        assertEquals(AccountDataManager.changeAccount(PASSWORD, NOTVALIDMAIL, PASSWORD, sessionStub), false);
        // when too short password
        assertEquals(AccountDataManager.changeAccount(PASSWORD, VALIDMAIL, SHORTPASSWORD, sessionStub), false);
        // when wrong password
        assertEquals(AccountDataManager.changeAccount(SHORTPASSWORD, VALIDMAIL, PASSWORD, sessionStub), false);
    }

    // Stubs
    class MessageBoxStub extends MessageBox {
        public MessageBoxStub() {
        }

        public void open() {
            System.out.println("This is a mock");
        }
    }
}
