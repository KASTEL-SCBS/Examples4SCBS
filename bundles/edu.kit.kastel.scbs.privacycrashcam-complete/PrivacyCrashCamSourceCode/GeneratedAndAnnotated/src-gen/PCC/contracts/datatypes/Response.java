package PCC.contracts.datatypes;

import java.lang.Iterable;
import java.util.ArrayList;
		
public class Response {
	
	private int status;
	private Iterable<Integer> body;
	
	public Response() {
		// TODO: Implement and verify auto-generated constructor.
		this.body = new ArrayList<Integer>();
	}
	
	public Response(int status, Iterable<Integer> body) {
		// TODO: Implement and verify auto-generated constructor.
		this.status = status;
		this.body = body;
	}
	
	public int getStatus() {
	    return status;
	}
	
	public void setStatus(int status) {
	    this.status = status;
	}
	
	public Iterable<Integer> getBody() {
	    return body;
	}
	
	public void setBody(Iterable<Integer> body) {
	    this.body = body;
	}
	
}