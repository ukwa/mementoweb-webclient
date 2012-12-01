package controllers;

import play.*;
import play.cache.Cache;
import play.data.*;
import play.mvc.*;
import play.libs.Json;

import views.html.*;

import models.Task;
import models.Query;

import uk.bl.wap.memento.MementoSearchBean;
import uk.bl.wap.memento.jsf.MementoBackingBean;

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

  static Form<Query> queryForm = form(Query.class);

  public static Result queryMementos() {
    return ok(
      views.html.search.render(new MementoBackingBean(), queryForm)
    );
  }

  public static Result findMementos() {
    Form<Query> urlParam = queryForm.bindFromRequest();
    System.err.println("ERROR "+urlParam.hasErrors());
    if(urlParam.hasErrors()) {
        return badRequest(
          views.html.search.render(new MementoBackingBean(), urlParam)
        );
    }
    Query q = urlParam.get();
    return redirect(routes.Application.findMementosFor(q.url));
//    return findMementosFor(q.url);
  }

  public static Result findMementosFor(String url) {
    MementoBackingBean msb = (MementoBackingBean) Cache.get("Mementos."+url);
    if( msb == null ) {
      msb = new MementoBackingBean();
      msb.setUrl(url);
    }
    Cache.set("Mementos."+url, msb);
    // Rebuild a Query object.
    Query q = new Query();
    q.url = url;
    return ok(
      views.html.search.render(msb, queryForm.fill(q))
    );
  }

}