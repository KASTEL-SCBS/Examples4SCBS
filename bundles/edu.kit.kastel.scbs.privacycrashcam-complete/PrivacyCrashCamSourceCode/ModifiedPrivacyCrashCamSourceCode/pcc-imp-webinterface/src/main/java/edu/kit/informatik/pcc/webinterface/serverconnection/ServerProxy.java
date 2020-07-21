package edu.kit.informatik.pcc.webinterface.serverconnection;

import edu.kit.informatik.pcc.webinterface.datamanagement.Account;


import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Created by chris on 17.01.2017.
 *
 * This class handles all communication with the Server.
 *
 * @author Josh Romanowski, Fabian Wenzel
 */
public class ServerProxy {

    //public static final String HOST = "http://laubenstone.de:2222/";
    public static final String HOST = "http://localhost:2222/";

    //status strings
    //TODO: Change to server connection failure -> handle
    private static final String FAILURE = "FAILURE";

    /**
     * Method does a Server Request to get the Videos from account a.
     * Then gives the answer back.
     *
     * @param a Account
     * @return Answer from Server.
     */
    public static String getVideos(String authenticationToken) {
        Response response = get("videos", authenticationToken, MediaType.APPLICATION_JSON);
        return (response == null) ? FAILURE : response.readEntity(String.class);
    }

    /**
     * This class gets the Video information from the Server.
     *
     * @param videoID Video which info is wanted
     * @param a       Account to which the video is referred
     * @return Answer from the Server
     */
    public static InputStream videoInfo(int videoID, String authenticationToken) {
        Response response = get("videos/metadata/" + videoID, authenticationToken);
        return response.readEntity(InputStream.class);
    }

    /**
     * This method gets the Video as File from the Server
     *
     * @param videoID Video which is wanted
     * @param a Account to which the video is referred
     * @return Answer from the Server
     */
    public static InputStream videoDownload(int videoID, String authenticationToken) {
    	   Response response = get("videos/" + videoID, authenticationToken);
           if (response == null)
               return null;

           return response.readEntity(InputStream.class);
    }
    
//    public static InputStream downloadVideo(int videoId, String authenticationToken) {
//    	Form f = new Form();
//    	f.param("videoId", Integer.toString(videoId));
//
//    	Response response = post(f, "videos/downloadVideo", authenticationToken);
//
//    	if(response == null)
//    		return null;
//    	
//    	return response.readEntity(InputStream.class);
//    }

    /**
     * This method deletes a Video from the Server.
     *
     * @param videoID Video which shall be deleted
     * @param a Account to which the video is referred
     * @return Answer from the Server
     */
    public static String videoDelete(int videoID, String authenticationToken) {
        Response response = delete("videos/" + videoID, authenticationToken);
        return (response == null) ? FAILURE : response.readEntity(String.class);
    }
    
    public static class AuthenticateResult {
    	public String result;
    	public String token;
    }

    /**
     * This method calls the Server to authenticate the given account.
     *
     * @param a Account
     * @return Answer from the Server.
     */
    public static AuthenticateResult authenticateUser(Account a) {
        Form f = new Form();
        f.param("mail", a.getMail());
        f.param("password", a.getPassword());

        Response response = post(f, "account/login", null);
        AuthenticateResult result = new ServerProxy.AuthenticateResult();
        if (response.getStatus() == 200) {
        	result.result = "SUCCESS";
        	result.token = response.getCookies().get("token").getValue();
        }
        else {
        	result.result = (response == null) ? FAILURE : response.readEntity(String.class);
        }
        return result;
    }

    /**
     * This method calls the Server to create an Account.
     *
     * @param a Account to create
     * @param id a unique id
     * @return Answer from the Server
     */
    public static String createAccount(Account a) {
        Form f = new Form();
        f.param("mail", a.getMail());
        f.param("password", a.getPassword());

        Response response = post(f, "account/create", null);
        return (response == null) ? FAILURE : response.readEntity(String.class);
    }

    /**
     * This method calls the server to change account date from old to new.
     *
     * @param oldAccount Account to change
     * @param newAccount New Account Data
     * @return Answer form the server
     */
    public static AuthenticateResult changeAccount(Account oldAccount, Account newAccount) {
    	return authenticateUser(newAccount);
    }

    /**
     * If you dont know what this does, dont use it.
     *
     * @param a Account to delete
     * @return Answer from the server
     */
    public static String deleteAccount(String authenticationToken) {
        Response response = delete("account/delete", authenticationToken);
        return (response == null) ? FAILURE : response.readEntity(String.class);
    }
    
    private static Response get(String path, String authenticationToken) {
    	return get(path, authenticationToken, null);
    }
    
    private static Response get(String path, String authenticationToken, String accepts) {
    	Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(HOST).path("webservice").path(path);
        Response response;
        try {
        	Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_FORM_URLENCODED);
        	if (accepts != null) {
        		builder = builder.header("Accept", accepts);
        	}
        	if (authenticationToken != null) {
        		builder = builder.header("token", authenticationToken);
        	}
            response = builder.get(Response.class);
		System.out.println("------");
		System.out.println(response);
        } catch (ProcessingException e) {
		System.out.println("!!------");
		e.printStackTrace();
            response = null;
        }
        client.close();
        return response;
    }
    
    private static Response delete(String path, String authenticationToken) {
    	Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(HOST).path("webservice").path(path);
        Response response;
        try {
        	Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_FORM_URLENCODED);
        	if (authenticationToken != null) {
        		builder = builder.header("token", authenticationToken);
        	}
            response = builder.delete(Response.class);
		System.out.println("------");
		System.out.println(response);
        } catch (ProcessingException e) {
		System.out.println("!!------");
		e.printStackTrace();
            response = null;
        }
        client.close();
        return response;
    }

    private static Response post(Form f, String path, String authenticationToken) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(HOST).path("webservice").path(path);
        Response response;
        try {
        	Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_FORM_URLENCODED);
        	if (authenticationToken != null) {
        		builder = builder.header("token", authenticationToken);
        	}
            response = builder.post(
                    Entity.entity(f, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Response.class);
		System.out.println("------");
		System.out.println(response);
        } catch (ProcessingException e) {
		System.out.println("!!------");
//		e.printStackTrace();
            response = null;
        }
        client.close();
        return response;
    }
}
