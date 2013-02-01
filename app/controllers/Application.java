package controllers;

import model.WorkFlow;

import org.codehaus.jackson.JsonNode;

import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
		JsonNode request = request().body().asJson();
		System.out.println(request.toString());
		System.out.println("nitin here");
		WorkFlow workFlow = new WorkFlow(request);
		System.out.println("context : "+ workFlow.getContext().toString());
		return ok(index.render("Your new application is ready."));
	}

}