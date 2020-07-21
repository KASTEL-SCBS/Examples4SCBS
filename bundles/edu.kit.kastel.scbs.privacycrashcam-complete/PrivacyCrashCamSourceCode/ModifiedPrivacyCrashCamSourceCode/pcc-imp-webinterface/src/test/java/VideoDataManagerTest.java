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


    @Test
    public void getVideosTest() {
        when(ServerProxy.getVideos(Mockito.anyObject())).thenReturn(jsonArray.toString());
        LinkedList<Video> videos = VideoDataManager.getVideos(sessionStub);
        assertEquals(videos.getFirst().getName(), video1.get(JSON_KEY_NAME));
        assertEquals(videos.getLast().getName(), video2.get(JSON_KEY_NAME));
        assertEquals(videos.getFirst().getId(), video1.get(JSON_KEY_ID));
        assertEquals(videos.getLast().getId(), video2.get(JSON_KEY_ID));
        assertEquals(videos.getFirst().getInfo(), "Date: 01:01:52.233 01.01.1970\n" +
                "Trigger type: trigger\n" +
                "G-Force (X): 1.0\n" +
                "G-Force (Y): 2.0\n" +
                "G-Force (Z): 3.0");
        assertEquals(videos.getLast().getInfo(), "Date: 01:01:52.233 01.01.1970\n" +
                "Trigger type: trigger\n" +
                "G-Force (X): 1.0\n" +
                "G-Force (Y): 2.0\n" +
                "G-Force (Z): 3.0");
    }

    @Test
    public void createDownloadResourceTest() {
        File file = new File(getClass().getResource("test.txt").getFile());
        InputStream stream = null;
        try {
            stream = new FileInputStream(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        when(ServerProxy.videoDownload(Mockito.eq(1), Mockito.anyObject())).thenReturn(stream);
        StreamResource streamResource = VideoDataManager.createDownloadFileProxy(1, "video1", sessionStub);
        assertTrue(streamResource.getStreamSource().getStream() != null);
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
