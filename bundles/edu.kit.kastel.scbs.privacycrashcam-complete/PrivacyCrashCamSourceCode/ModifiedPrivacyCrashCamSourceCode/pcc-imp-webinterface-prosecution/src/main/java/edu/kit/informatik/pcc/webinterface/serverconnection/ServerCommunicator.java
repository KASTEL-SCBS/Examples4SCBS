package edu.kit.informatik.pcc.webinterface.serverconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;

import de.steinwedel.messagebox.MessageBox;
import edu.kit.informatik.pcc.webinterface.datamanagement.Video;
import edu.kit.informatik.pcc.webinterface.serverconnection.ServerProxy.AuthenticateResult;

public class ServerCommunicator implements JudgeAccess, PersecutionInformationProvision, IUserManagement{
	private static final String FAILURE = "FAILURE";
    public static final String SUCCESS = "SUCCESS";
    public static final String WRONGACCOUNT = "WRONG ACCOUNT";
    public static final String ACCOUNTEXISTS = "ACCOUNT EXISTS";
    public static final String NOTEXISTING = "NOT EXISTING";
    public static final String WRONGPASSWORD = "WRONG PASSWORD";
    private static ResourceBundle errors = ResourceBundle.getBundle("ErrorMessages");
    private static ResourceBundle messages = ResourceBundle.getBundle("MessageBundle");

    
    
	private JudgeAccessWeb judgeCommunicator;
	private PersecutionInformationProvisionWeb persecutionCommunicator;
	private UserHandlingWeb userhandling;
	
	public ServerCommunicator() {
		ServerProxy proxy = new ServerProxy();
		judgeCommunicator = proxy;
		persecutionCommunicator = proxy;
		userhandling = proxy;
	}

	@Override
	public String getMetaData(int videoId, int targetUserId, String authenticationToken) {
		Response response = persecutionCommunicator.getMetaData(videoId, targetUserId, authenticationToken);
    	
    	
    	if(response == null) {
    		return null;
    	}
    	
    	InputStream inputStream = response.readEntity(InputStream.class);
    	
    	StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
          (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
			e.printStackTrace();
		}


    	
    	return textBuilder.toString();

	}

	@Override
	public String getAllVideoIds(String authenticationToken) {
		// TODO Auto-generated method stub

		return persecutionCommunicator.getAllVideoIds(authenticationToken);
	
	}

	@Override
	public int getUserIdForVideoId(int videoId, String authenticationToken) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InputStream getConfidentialVideo(int videoId, int targetUserId, String authenticationToken) {
		Response response = judgeCommunicator.getConfidentialVideo(videoId, targetUserId, authenticationToken);
		
		if(response == null) {
			return null;
		}
		
		return response.readEntity(InputStream.class);
		
	}
	
	 private int getUserId(String userIds) {
	    	try {
	    		JSONArray jsonArray = new JSONArray(userIds);
	    		
	    		if(jsonArray.length() != 0) {
	    			return jsonArray.getInt(0);
	    		}
	    	} catch (JSONException e) {
	            	e.printStackTrace();
	        }
	    	
			return -1;
	    }

	@Override
	public Boolean createAccount(String email, String password) {
		
		String result = userhandling.createAccount(email, password);
		 switch (result) {
         case SUCCESS:
             return true;
         case FAILURE:
             MessageBox.createInfo()
                     .withMessage(errors.getString("createFail"))
                     .open();
             return false;
         case ACCOUNTEXISTS:
             MessageBox.createInfo()
                     .withMessage(errors.getString("existingAccount"))
                     .open();
             return false;
         default:
             System.out.println(result);
             MessageBox.createInfo()
                     .withMessage(errors.getString("createFail"))
                     .open();
             return false;
     }

	}

	@Override
	public String login(String email, String password) {
		
		Response response = userhandling.login(email, password);
//		AuthenticateResult result = new ServerProxy.AuthenticateResult();
		String result = "";
        if (response.getStatus() == 200) {
        	result = response.getCookies().get("token").getValue();
        }
        else {
        	result = (response == null) ? FAILURE : response.readEntity(String.class);
        }
        return result;
	}

	@Override
	public Boolean deleteAccount(String authenticationToken) {
		String deleteResult = userhandling.deleteAccount(authenticationToken);
		

        switch (deleteResult) {
            case WRONGACCOUNT:
                MessageBox.createInfo()
                        .withMessage(errors.getString("wrongAccount"))
                        .open();
                break;
            case SUCCESS:
                MessageBox.createInfo()
                        .withMessage(errors.getString("accountDeleted"))
                        .open();
                return true;
            case FAILURE:
            default:
                System.out.println(deleteResult);
                MessageBox.createInfo()
                        .withMessage(errors.getString("deleteFail"))
                        .open();
                break;
        }
		return false;
	}
	
	public boolean changeAccount(String email, String password) {
		String accountChangeResult = login(email, password);
		 switch (accountChangeResult) {
         case WRONGACCOUNT:
             MessageBox.createInfo()
                     .withMessage(errors.getString("wrongAccount"))
                     .open();
             break;
         case SUCCESS:
         	
             MessageBox.createInfo()
                     .withMessage(errors.getString("accountChanged"))
                     .open();
             return true;
         case FAILURE:
         default:
             MessageBox.createInfo()
                     .withMessage(errors.getString("changeFail"))
                     .open();
             break;
     }
     return false;

	}

	
}
