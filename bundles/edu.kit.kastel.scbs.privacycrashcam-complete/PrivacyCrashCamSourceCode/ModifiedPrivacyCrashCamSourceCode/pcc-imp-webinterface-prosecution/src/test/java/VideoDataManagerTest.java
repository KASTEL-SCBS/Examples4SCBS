import com.vaadin.server.StreamResource;
import edu.kit.informatik.pcc.webinterface.datamanagement.Account;
import edu.kit.informatik.pcc.webinterface.datamanagement.Video;
import edu.kit.informatik.pcc.webinterface.datamanagement.VideoDataManager;
import edu.kit.informatik.pcc.webinterface.gui.VideoTable;
import edu.kit.informatik.pcc.webinterface.serverconnection.ServerProxy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Tests the VideoDataManager functionality
 *
 * @author Christop Hörtnagl
 * Created by chris on 03.03.2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ServerProxy.class)
public class VideoDataManagerTest {
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_ID = "id";
    private final static String JSON_KEY_DATE = "date";
    private final static String JSON_KEY_TRIGGER_TYPE = "triggerType";
    private final static String JSON_KEY_TRIGGER_FORCE_X = "triggerForceX";
    private final static String JSON_KEY_TRIGGER_FORCE_Y = "triggerForceY";
    private final static String JSON_KEY_TRIGGER_FORCE_Z = "triggerForceZ";
    private JSONObject meta;
    private SessionStub sessionStub;
    private JSONObject video1;
    private JSONObject video2;
    private float[] gforce;
    private String triggerType;
    private long date;
    private Account account;
    private JSONArray jsonArray;


    @Before
    public void setUp() {
        account = new Account("mail", "password");
        sessionStub = new SessionStub(null);
        jsonArray = new JSONArray();
        video1 = new JSONObject(getAsJson("video1", 1));
        video2 = new JSONObject(getAsJson("video2", 2));
        jsonArray.put(0, video1);
        jsonArray.put(1, video2);
        gforce = new float[]{1.0f, 2.0f, 3.0f};
        date = 112233;
        triggerType = "trigger";
        meta = new JSONObject(getAsJSON(date, triggerType, gforce));
        PowerMockito.mockStatic(ServerProxy.class);
    }

    public String getAsJson(String videoName, int videoId) {
        JSONObject json = new JSONObject();
        try {
            json.put(JSON_KEY_NAME, videoName);
            json.put(JSON_KEY_ID, videoId);
        } catch (JSONException e) {
            Assert.fail();
        }
        return json.toString();
    }

    public String getAsJSON(long date, String triggerType, float[] gForce) {
        JSONObject json = new JSONObject();
        try {
            json.put(JSON_KEY_DATE, date);
            json.put(JSON_KEY_TRIGGER_TYPE, triggerType);
            json.put(JSON_KEY_TRIGGER_FORCE_X, gForce[0]);
            json.put(JSON_KEY_TRIGGER_FORCE_Y, gForce[1]);
            json.put(JSON_KEY_TRIGGER_FORCE_Z, gForce[2]);
        } catch (JSONException e) {
            Assert.fail();
        }
        return json.toString();
    }
}
