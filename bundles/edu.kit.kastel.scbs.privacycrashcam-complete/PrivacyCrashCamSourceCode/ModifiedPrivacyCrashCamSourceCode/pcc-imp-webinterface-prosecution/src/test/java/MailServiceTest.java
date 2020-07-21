import edu.kit.informatik.pcc.webinterface.mailservice.MailService;
import org.apache.commons.mail.EmailException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests the MailService's mail check functionality.
 *
 * @author Christoph Hörtnagl
 * Created by chris on 03.03.2017.
 */
public class MailServiceTest {
    private final String VALIDMAIL = "mail@mail.mail";
    private final String NOTVALIDMAIL = "mail";

    @Test
    public void isValidMailTest() {
        assertEquals(MailService.isValidEmailAddress(VALIDMAIL), true);
        assertEquals(MailService.isValidEmailAddress(NOTVALIDMAIL), false);
    }

    @Test
    public void isValidMailFailTest() {
        assertEquals(MailService.isValidEmailAddress(NOTVALIDMAIL), false);
    }

    @Test(expected = EmailException.class)
    public void sendMailFailTest() throws IOException, EmailException {
        MailService.send(VALIDMAIL, NOTVALIDMAIL, "hallo", "abc");
    }
}
