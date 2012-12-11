package models;

import java.util.*;

import play.data.validation.Constraints.*;

public class Task {
    
  public Long id;

  @Required
  public String label;

  private static List<Task> tasks = new ArrayList<Task>();
  
  public static List<Task> all() {
    return tasks;
  }
  
  public static void create(Task task) {
    if( task.id == null ) {
        task.id = new Long( tasks.size() );
    }
    tasks.add(task);
  }
  
  public static void delete(Long id) {
    for( Task t : tasks ) {
        if( t.id != null && t.id.equals(id) ) {
            tasks.remove(t);
            break;
        }
    }
  }  
    
}