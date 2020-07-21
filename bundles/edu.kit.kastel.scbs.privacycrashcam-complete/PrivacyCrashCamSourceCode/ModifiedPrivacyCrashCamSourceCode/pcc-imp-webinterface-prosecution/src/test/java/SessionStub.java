import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import edu.kit.informatik.pcc.webinterface.datamanagement.Account;

/**
 * @author Christop Hörtnagl
 * Created by chris on 03.03.2017.
 */
public class SessionStub extends VaadinSession {
    private Account account;

    SessionStub(VaadinService service) {
        super(service);
        account = new Account("mail", "password");
    }

    public Object getAttribute(String name) {
        System.out.println("You used a stub");
        return account;
    }

}