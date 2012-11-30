package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import play.libs.Json;

import views.html.*;

import models.Task;

import uk.bl.wap.memento.MementoSearchBean;

public class Application extends Controller {

  static Form<Task> taskForm = form(Task.class);

 public static Result index() {
    return redirect(routes.Application.tasks());
    //return ok(index.render("Your new application is ready."));
  }
  
  public static Result tasks() {
    return ok(
      views.html.index.render(Task.all(), taskForm)
    );
  }
  
  public static Result newTask() {
  Form<Task> filledForm = taskForm.bindFromRequest();
  if(filledForm.hasErrors()) {
    return badRequest(
      views.html.index.render(Task.all(), filledForm)
    );
  } else {
    Task.create(filledForm.get());
    return redirect(routes.Application.tasks());  
  }
  }
  
  public static Result deleteTask(Long id) {
  Task.delete(id);
  return redirect(routes.Application.tasks());
  }

  /**
   * Return Mementos as JSON:
   */
  public static Result findMementosApi(String url) {
    MementoSearchBean msb = new MementoSearchBean();
    msb.setUrl(url);
    if (request().accepts("text/html")) {
          return ok("html "+url+"\n"+msb+"\n");
        } else if (request().accepts("application/json")) {
          return ok(Json.toJson(msb));
        } else {
          return badRequest();
       }
  }

  public static Result findMementos(String url) {
    MementoSearchBean msb = new MementoSearchBean();
    msb.setUrl(url);
    return ok(
      views.html.mementos.render(msb)
    );
  }

}