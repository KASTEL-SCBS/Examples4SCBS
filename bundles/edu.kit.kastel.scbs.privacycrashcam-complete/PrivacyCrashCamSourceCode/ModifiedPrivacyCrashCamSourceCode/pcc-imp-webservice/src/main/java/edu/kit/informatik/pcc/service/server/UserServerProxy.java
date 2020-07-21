package edu.kit.informatik.pcc.service.server;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("webservice/account")
public class UserServerProxy implements UserHandlingWeb{

    //param strings
    private final static String EMAIL    	= "mail";
    private final static String PASSWORD 	= "password";
    
    @POST
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Path("create")
    public String createAccount(@FormParam(EMAIL) String email, @FormParam(PASSWORD) String password) {
        Logger.getGlobal().info("Account Creation Request");
        Boolean success = AnonymizationService.getGlobal().createAccount(email, password);
        return success ? ServerConstants.SUCCESS : ServerConstants.FAILURE;
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Path("login")
    public Response login(@FormParam(EMAIL) String email, @FormParam(PASSWORD) String password) {
    	Logger.getGlobal().info("Account Login Request");
    	String authenticationToken = AnonymizationService.getGlobal().login(email, password);
    	if (authenticationToken == null) {
    		return Response.status(401).entity(ServerConstants.WRONG_PASSWORD).build();
    	}
    	else {
    		ResponseBuilder response = Response.ok();
    		response.cookie(new NewCookie("token", authenticationToken));
    		return response.build();
    	}
    }
    
    @POST
    @Path("delete")
    public String deleteAccount(@HeaderParam(ServerConstants.TOKEN) String authenticationToken) {
        Logger.getGlobal().info("Account Deletion Request");
        Boolean success = AnonymizationService.getGlobal().deleteAccount(authenticationToken);
        return success ? ServerConstants.SUCCESS : ServerConstants.NOT_AUTHENTICATED;
    }
}
