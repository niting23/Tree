package model;

import java.util.HashMap;

import play.db.ebean.*;

public class Step extends Model{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1483418627012612405L;
	private String service = null;
    private String action = null;
    private HashMap request = null;

    public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public HashMap getRequest() {
		return request;
	}

	public void setRequest(HashMap request) {
		this.request = request;
	}

	/**
     * Processes the step of the transaction.
     * Functional implementation of step goes here.
     *
     * @param cnxt
     * @throws Exception
     */
    public void processStep(Context cnxt) throws Exception{

       //call helper service
    }

}
